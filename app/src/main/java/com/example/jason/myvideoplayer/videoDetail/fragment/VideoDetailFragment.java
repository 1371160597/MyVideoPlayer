package com.example.jason.myvideoplayer.videoDetail.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.base.LogUtil;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtilManager;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtilUrl;
import com.example.jason.myvideoplayer.mainPage.util.Parameter;
import com.example.jason.myvideoplayer.videoDetail.adapter.VideoMessageRecyclerAdapter;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageGson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType3Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType4Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlay2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlayGson;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType1;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType2;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType3;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class VideoDetailFragment extends Fragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, View.OnClickListener, MediaPlayer.OnVideoSizeChangedListener {


    public static final int PTYPE1 = 0;//标题+视频详细信息
    public static final int PTYPE2 = 1;//关注量+图片
    public static final int PTYPE3 = 2;//正片类型1
    public static final int PTYPE4 = 3;//花絮预告
    public static final int PTYPE5 = 4;//正片类型2
    private TextView text;
    private String jumpId;
    private String jumpMessage;
    private String jumpChildId;
    private String jumpKind;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private MediaPlayer player;
//    private String url = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    private String url = null;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private TextView showTime;
    private TextView allTime;
    private ImageView imageBack;
    private ImageView imagePlayAndPause;
    private ImageView imageAllScreen;
    private boolean seekBarAutoFlag;
    private boolean isClick = false;
    private int isAllScreen = 2;

    private Thread thread = new Thread() {

        public void run() {
            // TODO Auto-generated method stub
            super.run();
            // 增加对异常的捕获，防止在判断mediaPlayer.isPlaying的时候，报IllegalStateException异常
            try {
                while (seekBarAutoFlag) {
                   /*
                    * mediaPlayer不为空且处于正在播放状态时，使进度条滚动。
                    */
                    if (null != player && player.isPlaying()) {
                        seekBar.setProgress(player.getCurrentPosition());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };
    private String videoTimeString;
    private LinearLayout functionLayout;
    private long position = 0;
//    private VideoDetailPlayGson result;
    private RecyclerView detailRecycler;
    private VideoMessageRecyclerAdapter adapter;
    private List<VideoMessageRecyclerAdapter.VideoMessage> data;
    private VideoDetailMessageGson resultTitle;
    private VideoDetailMessageType2Gson resultType2;
    private VideoDetailMessageType3Gson resultType3;
    private VideoDetailMessageType4Gson resultType4;
    private VideoDetailMessageType3Gson resultType5;


    public VideoDetailFragment(Parameter parameter) {
        jumpId = parameter.getJumpId();
        jumpMessage = parameter.getJumpMessage();
        jumpChildId = parameter.getJumpChildId();
        jumpKind = parameter.getJumpKind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_detail, container, false);
        text = (TextView) view.findViewById(R.id.text);
        progressBar = (ProgressBar) view.findViewById(R.id.video_detail_progressbar);
        surfaceView = (SurfaceView) view.findViewById(R.id.surface_view);
        seekBar = (SeekBar) view.findViewById(R.id.video_detail_seek_bar);
        showTime = (TextView) view.findViewById(R.id.video_detail_text_start_time);
        allTime = (TextView) view.findViewById(R.id.video_detail_text_all_time);
        imageBack = (ImageView) view.findViewById(R.id.video_detail_back);
        imagePlayAndPause = (ImageView) view.findViewById(R.id.video_detail_image_play);
        imageAllScreen = (ImageView) view.findViewById(R.id.video_detail_image_all_screen);
        functionLayout = (LinearLayout) view.findViewById(R.id.video_detail_function_layout);
        detailRecycler = (RecyclerView) view.findViewById(R.id.video_detail_recycler_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text.setText("JUmpId:" + jumpId+",JumpChildId"+jumpChildId);

        requestHttpForVideoDetailPlay(jumpMessage);
        requestHttpForVideoMessageTitle(jumpMessage);
    }

    private void requestHttpForVideoMessageTitle(final String jumpMessage) {
        String address = null;
        if (jumpMessage.equals("banner")) {
            address = HttpUtilUrl.getBannerVideoMessage(jumpId);
        } else if (jumpMessage.equals("item_new")) {
            if (!TextUtils.isEmpty(jumpChildId)) {
                address = HttpUtilUrl.getRecyclerItemVideoMessage(jumpChildId, jumpId);
            } else {
                address = HttpUtilUrl.getBannerVideoMessage(jumpId);
            }
//            address = HttpUtilUrl.getRecyclerItemVideoMessage(jumpChildId, jumpId);
        }
        LogUtil.e(LogUtil.TAG,"ADDRESS444:"+address);
        HttpUtilManager.getInstance().getVideoDetailMessageItem1(address, new HttpUtilManager.CallBack<VideoDetailMessageGson>() {
            @Override
            public void onSuccess(VideoDetailMessageGson result) {
                if (result.data == null)
                    return;
                resultTitle = result;
                initVideoMessageRecyclerAdapter(jumpKind);

                int sizeCount = result.data.categoryList.size();
                for (int i = 0; i < sizeCount; i++) {
                    int displayType = result.data.categoryList.get(i).displayType;
                    if (displayType == 7) {
                        //获取关注量 url
                        String addressType2 = result.data.categoryList.get(i).url;
                        requestHttpForVideoMessageType2(addressType2);
                    } else if (displayType == 2) {
                        //获取正片类型1的集数 url
                        String addressType3 = result.data.categoryList.get(i).url + "&clipId=" + jumpId;
                        requestHttpForVideoMessageType3(addressType3, i);
                    } else if (displayType == 3) {
                        if (jumpMessage.equals("banner")) {
                            //获取花絮片段 url
                            String addressType4 = result.data.categoryList.get(i).url;
                            requestHttpForVideoMessageType4(addressType4, i);
                        } else if (jumpMessage.equals("item_new")) {
                            String addressType4 = result.data.categoryList.get(i).url;
                            requestHttpForVideoMessageType4(addressType4, i);
                        }
                    } else if (displayType == 1 && result.data.categoryList.get(i).ltitle.equals("正片")) {
                        //获取正片类型2的集数 url
                        String addressType5 = result.data.categoryList.get(i).url + "&clipId=" + jumpId;
                        requestHttpForVideoMessageType5(addressType5, i);
                    }
                }
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHttpForVideoMessageType2(String addressType2) {
        HttpUtilManager.getInstance().getVideoDetailMessageItem2(addressType2, new HttpUtilManager.CallBack<VideoDetailMessageType2Gson>() {
            @Override
            public void onSuccess(VideoDetailMessageType2Gson result) {
                if (result.data == null)
                    return;
                resultType2 = result;
                initVideoMessageRecyclerContent(PTYPE2, 0);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHttpForVideoMessageType3(String addressType3, final int position) {
        HttpUtilManager.getInstance().getVideoDetailMessageItem3(addressType3, new HttpUtilManager.CallBack<VideoDetailMessageType3Gson>() {
            @Override
            public void onSuccess(VideoDetailMessageType3Gson result) {
                if (result.data == null)
                    return;
                resultType3 = result;
                initVideoMessageRecyclerContent(PTYPE3,position);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHttpForVideoMessageType4(String addressType4, final int position) {
        HttpUtilManager.getInstance().getVideoDetailMessageItem4(addressType4, new HttpUtilManager.CallBack<VideoDetailMessageType4Gson>() {
            @Override
            public void onSuccess(VideoDetailMessageType4Gson result) {
                if (result.data == null)
                    return;
                resultType4 = result;
                initVideoMessageRecyclerContent(PTYPE4,position);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHttpForVideoMessageType5(String addressType5, final int position) {
        HttpUtilManager.getInstance().getVideoDetailMessageItem3(addressType5, new HttpUtilManager.CallBack<VideoDetailMessageType3Gson>() {
            @Override
            public void onSuccess(VideoDetailMessageType3Gson result) {
                if (result.data == null)
                    return;
                resultType5 = result;
                initVideoMessageRecyclerContent(PTYPE5,position);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initVideoMessageRecyclerAdapter(String jumpKind) {
        adapter = new VideoMessageRecyclerAdapter(null);
        detailRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        detailRecycler.setAdapter(adapter);
        adapter.setNewData(getData(PTYPE1,0,jumpKind));
    }

    private void initVideoMessageRecyclerContent(int type,int position) {
        adapter.setNewData(getData(type,position,jumpKind));
        adapter.notifyDataSetChanged();
    }

    public List<VideoMessageRecyclerAdapter.VideoMessage> getData(int flag,int position,String jumpKind) {
//        List<VideoMessageRecyclerAdapter.VideoMessage> data = new ArrayList<>();
        if (data == null) {
            data = new ArrayList<>();
        }

        switch (jumpKind) {
            case "1":
            case "12":
                switch (flag) {
                    case PTYPE1:
                        String title, scores, number, update;
                        title = resultTitle.data.title;
                        scores = resultTitle.data.scores;
                        number = resultTitle.data.fstlvlName;
                        update = resultTitle.data.updateTips;
                        int size = resultTitle.data.detail.size();
                        int bigSize = 7;
                        String[] textMessage = new String[bigSize];
                        for (int i = 0; i < bigSize; i++) {
                            if (i < size) {
                                textMessage[i] = resultTitle.data.detail.get(i);
                            } else {
                                textMessage[i] = "";
                            }
                        }
                        RecyclerType1 recyclerType1 = new RecyclerType1(title,scores,number,update,textMessage[0],textMessage[1],textMessage[2],textMessage[3],textMessage[4],textMessage[5],textMessage[6]);
                        data.add(new VideoMessageRecyclerAdapter.VideoMessage(recyclerType1, VideoMessageRecyclerAdapter.VideoMessage.TYPE1));
                        break;
                    case PTYPE2:
                        String title2, number2, imageUrl2;
                        title2 = resultType2.data.get(0).nickName;
                        number2 = resultType2.data.get(0).formatCollectionCounts;
                        imageUrl2 = resultType2.data.get(0).photo;
                        RecyclerType2 recyclerType2 = new RecyclerType2(imageUrl2, title2, number2);
                        data.add(new VideoMessageRecyclerAdapter.VideoMessage(recyclerType2, VideoMessageRecyclerAdapter.VideoMessage.TYPE2));
                        break;
                    case PTYPE3:
                        List<RecyclerType3> type3List = new ArrayList<>();
                        int size3 = resultType3.data.list.size();
                        String title3, image3, desc3, name3, info3, videoId3, videoIndex3, color3, font3;
                        RecyclerType3 recyclerType3 = null;
                        for (int i = 0; i < size3; i++) {
                            title3 = resultTitle.data.categoryList.get(position).ltitle;
                            image3 = resultType3.data.list.get(i).image;
                            desc3 = resultType3.data.list.get(i).desc;
                            name3 = resultType3.data.list.get(i).name;
                            info3 = resultType3.data.list.get(i).info;
                            videoId3 = resultType3.data.list.get(i).videoId;
                            videoIndex3 = String.valueOf(resultType3.data.list.get(i).videoIndex);
                            color3 = resultType3.data.list.get(i).cornerLabelStyle.color;
                            font3 = resultType3.data.list.get(i).cornerLabelStyle.font;
                            recyclerType3 = new RecyclerType3(title3, image3, desc3, name3, info3, videoId3, videoIndex3, color3, font3);
                            type3List.add(recyclerType3);
                        }
                        data.add(new VideoMessageRecyclerAdapter.VideoMessage(type3List, VideoMessageRecyclerAdapter.VideoMessage.TYPE3,0));
                        break;
                    case PTYPE4:
                        List<RecyclerType4> type4List = new ArrayList<>();
                        int size4 = resultType4.data.list.size();
                        String title4,image4, desc4, info4, name4, videoId4;
                        RecyclerType4 recyclerType4;
                        for (int i = 0; i < size4; i++) {
                            title4 = resultTitle.data.categoryList.get(position).ltitle;
                            image4 = resultType4.data.list.get(i).image;
                            desc4 = resultType4.data.list.get(i).desc;
                            info4 = resultType4.data.list.get(i).info;
                            name4 = resultType4.data.list.get(i).name;
                            videoId4 = resultType4.data.list.get(i).videoId;
                            recyclerType4 = new RecyclerType4(title4,image4, desc4, info4, name4, videoId4);
                            type4List.add(recyclerType4);
                        }
                        data.add(new VideoMessageRecyclerAdapter.VideoMessage(type4List, VideoMessageRecyclerAdapter.VideoMessage.TYPE4));
                        break;
                    case PTYPE5:
                        List<RecyclerType3> type5List = new ArrayList<>();
                        int size5 = resultType5.data.list.size();
                        String title5, image5, desc5, name5, info5, videoId5, videoIndex5, color5, font5;
                        RecyclerType3 recyclerType5 = null;
                        for (int i = 0; i < size5; i++) {
                            title5 = resultTitle.data.categoryList.get(position).ltitle;
                            image5 = resultType5.data.list.get(i).image;
                            desc5 = resultType5.data.list.get(i).desc;
                            name5 = resultType5.data.list.get(i).name;
                            info5 = resultType5.data.list.get(i).info;
                            videoId5 = resultType5.data.list.get(i).videoId;
                            videoIndex5 = String.valueOf(resultType5.data.list.get(i).videoIndex);
                            color5 = resultType5.data.list.get(i).cornerLabelStyle.color;
                            font5 = resultType5.data.list.get(i).cornerLabelStyle.font;
                            recyclerType5 = new RecyclerType3(title5, image5, desc5, name5, info5, videoId5, videoIndex5, color5, font5);
                            type5List.add(recyclerType5);
                        }
                        data.add(new VideoMessageRecyclerAdapter.VideoMessage(type5List, VideoMessageRecyclerAdapter.VideoMessage.TYPE5,2));
                        break;
                }
                break;
            case "13":
                Log.e(LogUtil.TAG, "getData: 新闻ces" );
                break;
        }
        return data;
    }

    private void requestHttpForVideoDetailPlay(final String message) {
        String address = null;
        if (message.equals("banner")) {
            address = HttpUtilUrl.getBannerVideoPlay(jumpId);
        } else if (message.equals("item_new")) {
            if (!TextUtils.isEmpty(jumpChildId)) {
                LogUtil.e(LogUtil.TAG,"jumpChildId:111:"+jumpChildId);
                address = HttpUtilUrl.getRecyclerItemVideoPlay(jumpChildId, jumpId);
            } else {
                address = HttpUtilUrl.getBannerVideoPlay(jumpId);
                LogUtil.e(LogUtil.TAG,"jumpChildId:222:"+jumpChildId);
            }
//            address = HttpUtilUrl.getRecyclerItemVideoPlay(jumpChildId, jumpId);
        }
        LogUtil.e(LogUtil.TAG,"ADDRESS111:"+address);
        HttpUtilManager.getInstance().getVideoDetailPlay1(address, new HttpUtilManager.CallBack<VideoDetailPlayGson>() {
            @Override
            public void onSuccess(VideoDetailPlayGson result) {
                if(result.data == null)
                    return;
                String address = null;
                if (message.equals("banner")) {
                    //get[1]代表超清
                    address = result.data.videoDomains.get(0) + result.data.videoSources.get(1).url;
                } else if (message.equals("item_new")){
                    if (!TextUtils.isEmpty(jumpChildId)) {
                        //get[0]代表超清
                        address = result.data.videoDomains.get(0) + result.data.videoSources.get(0).url;
                    } else {
                        address = result.data.videoDomains.get(0) + result.data.videoSources.get(1).url;
                    }
                    LogUtil.e(LogUtil.TAG,"ADDRESS222:"+address);
                }
                requestHttpForVideoDetail2Play(address);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHttpForVideoDetail2Play(String address) {
        LogUtil.e(LogUtil.TAG,"ADDRESS333:"+address);
        HttpUtilManager.getInstance().getVideoDetailPlay2(address, new HttpUtilManager.CallBack<VideoDetailPlay2Gson>() {
            @Override
            public void onSuccess(VideoDetailPlay2Gson data) {
                if (data.info == null)
                    return;
                String urlInfo = data.info;
                initSurfaceHolder(urlInfo);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initSurfaceHolder(final String myUrl) {
        surfaceView.setVisibility(View.GONE);
        holder = surfaceView.getHolder();
        url = myUrl;
        // 设置Holder类型,该类型表示surfaceView自己不管理缓存区,虽然提示过时，但最好还是要设置
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 设置surface回调
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                // surfaceView被创建
                // 设置播放资源
                playVideo();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {
                // SurfaceView的大小改变
//                ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
//                lp.width = 1080;
//                lp.height =608;
//                surfaceView.setLayoutParams(lp);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                // surfaceView销毁
                // 如果MediaPlayer没被销毁，则销毁mediaPlayer
                if (player != null) {
                    player.release();
                    player = null;
                }
            }
        });
        surfaceView.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        imagePlayAndPause.setOnClickListener(this);
        imageAllScreen.setOnClickListener(this);

        surfaceView.setVisibility(View.VISIBLE);
    }

    /**
     * 播放视频
     */
    private void playVideo() {
        if (player == null) {
            player = new MediaPlayer();
        }
        // 重置mediaPaly,建议在初始滑mediaplay立即调用。
        player.reset();
        // 设置声音效果
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置播放完成监听
        player.setOnCompletionListener(this);
        // 设置媒体加载完成以后回调函数
        player.setOnPreparedListener(this);
        // 错误监听回调函数
        player.setOnErrorListener(this);
        // 设置缓存变化监听
        player.setOnBufferingUpdateListener(this);
        //视频尺寸监听
        player.setOnVideoSizeChangedListener(this);
        try {
            player.setDataSource(getContext(),Uri.parse(url));
            // 设置异步加载视频，包括两种方式 prepare()同步，prepareAsync()异步
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeVideoScreenSize() {
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        if (isAllScreen == 1) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth,
                    screenHeight);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            surfaceView.setLayoutParams(layoutParams);
        } else if (isAllScreen == 2) {
            int width = player.getVideoWidth();
            int height = player.getVideoHeight();
            float percent = (float) screenWidth / (float) width;
            float realHeight = (float) height *  percent;
            int realH = (int) Math.ceil(realHeight);
            Log.d(LogUtil.TAG, "1.width: "+width);
            Log.d(LogUtil.TAG, "2.height: "+height);
            Log.d(LogUtil.TAG, "3.screenWidth: "+screenWidth);
            Log.d(LogUtil.TAG, "4.screenHeight: "+screenHeight);

            Log.d(LogUtil.TAG, "5.realHeight: "+realHeight);
            Log.d(LogUtil.TAG, "6.realH: "+realH);
            Log.d(LogUtil.TAG, "7.percent: "+percent);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth, realH);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            surfaceView.setLayoutParams(layoutParams);
        }else  if (isAllScreen == 3){
            //小窗口播放
            int width = player.getVideoWidth();
            int height = player.getVideoHeight();
            if (width > screenWidth || height > screenHeight) {
                // 计算出宽高的倍数
                float vWidth = (float) width / (float) screenWidth;
                float vHeight = (float) height / (float) screenHeight;
                // 获取最大的倍数值，按大数值进行缩放
                float max = Math.max(vWidth, vHeight);
                // 计算出缩放大小,取接近的正值
                width = (int) Math.ceil((float) width / max);
                height = (int) Math.ceil((float) height / max);
            }
            // 设置SurfaceView的大小并居中显示
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width,
                    height);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            surfaceView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        changeVideoScreenSize();
        // 当视频加载完毕以后，隐藏加载进度条
        progressBar.setVisibility(View.GONE);
        player.start();
        // 设置显示到屏幕
        player.setDisplay(holder);
        // 设置surfaceView保持在屏幕上
        player.setScreenOnWhilePlaying(true);
        holder.setKeepScreenOn(true);
        // 设置控制条,放在加载完成以后设置，防止获取getDuration()错误
        seekBar.setProgress(0);
        seekBar.setMax(player.getDuration());

        // 设置播放时间
        videoTimeString = getShowTime(player.getDuration());
        showTime.setText("00:00:00");
        allTime.setText(videoTimeString);
        // 设置拖动监听事件
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());

        seekBarAutoFlag = true;
        // 开启线程 刷新进度条
        thread.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
//        Log.d("TAG", "视频播放完成");
        imagePlayAndPause.setImageResource(R.drawable.video_detail_play);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
//        Log.d("TAG", "视频播放错误");
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
//        Log.d("TAG", "视频播放更新");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {
        if (width == 0 || height == 0) {
            return;
        }
    }

    //各控件的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.surface_view:
                if (isClick) {
                    functionLayout.setVisibility(View.VISIBLE);
                    isClick = false;
                } else {
                    functionLayout.setVisibility(View.INVISIBLE);
                    isClick = true;
                }
                break;
            case R.id.video_detail_back:
                getActivity().finish();
                break;
            case R.id.video_detail_image_play:
                if (player.isPlaying()) {
                    player.pause();
                    imagePlayAndPause.setImageResource(R.drawable.video_detail_play);
                } else {
                    player.start();
                    imagePlayAndPause.setImageResource(R.drawable.video_detail_pause);
                }
                
                break;
            case R.id.video_detail_image_all_screen:
                Toast.makeText(getContext(), "全屏显示", Toast.LENGTH_SHORT).show();
//                isAllScreen = 1;
//                playVideo();
                break;
        }
    }

    /**
     * seekBar拖动监听类
     *
     * @author shenxiaolei
     */
    @SuppressWarnings("unused")
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // TODO Auto-generated method stub
            if (progress >= 0) {
                // 如果是用户手动拖动控件，则设置视频跳转。
                if (fromUser) {
                    player.seekTo(progress);
                }
                // 设置当前播放时间
                showTime.setText(getShowTime(progress));
                allTime.setText(videoTimeString);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }
    }

    /**
     * 转换播放时间
     *
     * @param milliseconds 传入毫秒值
     * @return 返回 hh:mm:ss或mm:ss格式的数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    public String getShowTime(long milliseconds) {
        // 获取日历函数
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        SimpleDateFormat dateFormat = null;
        // 判断是否大于60分钟，如果大于就显示小时。设置日期格式
        if (milliseconds / 60000 > 60) {
            dateFormat = new SimpleDateFormat("hh:mm:ss");
        } else {
            dateFormat = new SimpleDateFormat("mm:ss");
        }
        return dateFormat.format(calendar.getTime());
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("TAG", "onResume");
//        if (player != null) {
//            player.seekTo((int) position);
//            player.start();
//        }

    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.d("TAG", "onPause");
//        player.pause();
//        position = player.getCurrentPosition();
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.d("TAG", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
//        Log.d("TAG", "onStop");
//        player.pause();
//        position = player.getCurrentPosition();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d("TAG", "onDestroy");
    }
}
