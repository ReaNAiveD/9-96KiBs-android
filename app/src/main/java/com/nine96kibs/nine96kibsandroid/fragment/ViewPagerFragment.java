package com.nine96kibs.nine96kibsandroid.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nine96kibs.nine96kibsandroid.CommonResult;
import com.nine96kibs.nine96kibsandroid.MainActivity;
import com.nine96kibs.nine96kibsandroid.R;
import com.nine96kibs.nine96kibsandroid.collect.Collection;
import com.nine96kibs.nine96kibsandroid.collect.CollectionAdapter;
import com.nine96kibs.nine96kibsandroid.collect.CollectionChild;
import com.nine96kibs.nine96kibsandroid.collect.CollectionParent;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTask;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTaskAdapter;
import com.nine96kibs.nine96kibsandroid.remember.RememberText;
import com.nine96kibs.nine96kibsandroid.remember.RememberTextAdapter;
import com.nine96kibs.nine96kibsandroid.vo.TaskInfoVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ViewPagerFragment extends Fragment {

    RecyclerView.Adapter reciteAdapter;
    RecyclerView.Adapter rememberAdapter;
    RecyclerView.Adapter collectAdapter;

    public ViewPagerFragment() {

        new Thread(() -> {
            try {
                Gson gson = new Gson();
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder().url("http://47.100.97.17:8848/classic-poetry/normal/task-info?user-id="+ MainActivity.userId).get().build();
                Log.d("ViewPagerFragment", "http://47.100.97.17:8848/classic-poetry/normal/task-info?user-id="+ MainActivity.userId);
                Response response = client.newCall(request).execute();
                CommonResult commonResult = gson.fromJson(response.body().string(), CommonResult.class);
                Log.d("ViewPagerFragment", "ViewPagerFragment: " + gson.toJson(commonResult.getData()));
                List<TaskInfoVO> taskInfoVOList = gson.fromJson(gson.toJson(commonResult.getData()), new TypeToken<List<TaskInfoVO>>(){}.getType());
                List<ReciteTask> reciteTasks = new ArrayList<>();
                for (TaskInfoVO taskInfoVO : taskInfoVOList) {
                    reciteTasks.add(new ReciteTask(taskInfoVO.getTaskId(), taskInfoVO.getTaskName(), taskInfoVO.getTaskReciteCommand(), taskInfoVO.getTaskReciteLearning() - taskInfoVO.getTaskReciteCommand(), taskInfoVO.getTaskReciteTotal() - taskInfoVO.getTaskReciteLearning()));
                }
                reciteAdapter = new ReciteTaskAdapter(reciteTasks);
            } catch (IOException e){
                e.printStackTrace();
            }
        }).start();

        List<RememberText> rememberTexts = new ArrayList<>();
        rememberTexts.add(new RememberText("君不见，黄河之水天上来，奔流到海不复回。\n" +
                "君不见，高堂明镜悲白发，朝如青丝暮成雪！\n" +
                "人生得意须尽欢，莫使金樽空对月。\n" +
                "天生我材必有用，千金散尽还复来。\n" +
                "烹羊宰牛且为乐，会须一饮三百杯。\n" +
                "岑夫子，丹丘生，将进酒，杯莫停。\n" +
                "与君歌一曲，请君为我倾耳听。\n" +
                "钟鼓馔玉不足贵，但愿长醉不复醒。\n" +
                "古来圣贤皆寂寞，惟有饮者留其名。\n" +
                "陈王昔时宴平乐，斗酒十千恣欢谑。\n" +
                "主人何为言少钱，径须沽取对君酌。\n" +
                "五花马、千金裘，呼儿将出换美酒，与尔同销万古愁！", "—— 李白《将进酒》"));
        rememberTexts.add(new RememberText("\u3000\u3000" + "自三峡七百里中，两岸连山，略无阙处。重岩叠嶂，隐天蔽日，自非亭午夜分，不见曦月。\n" +
                "\u3000\u3000" + "至于夏水襄陵，沿溯阻绝。或王命急宣，有时朝发白帝，暮到江陵，其间千二百里，虽乘奔御风，不以疾也。\n" +
                "\u3000\u3000" + "春冬之时，则素湍绿潭，回清倒影，绝巘多生怪柏，悬泉瀑布，飞漱其间，清荣峻茂，良多趣味。\n" +
                "\u3000\u3000" + "每至晴初霜旦，林寒涧肃，常有高猿长啸，属引凄异，空谷传响，哀转久绝。故渔者歌曰：“巴东三峡巫峡长，猿鸣三声泪沾裳。", "—— 郦道元《三峡》"));
        rememberTexts.add(new RememberText("\u3000\u3000" + "我说道：“爸爸，你走吧。”他望车外看了看，说：“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。我本来要去的，他不肯，只好让他去。我看见他戴着黑布小帽，穿着黑布大马褂，深青布棉袍，蹒跚地走到铁道边，慢慢探身下去，尚不大难。可是他穿过铁道，要爬上那边月台，就不容易了。他用两手攀着上面，两脚再向上缩；他肥胖的身子向左微倾，显出努力的样子。这时我看见他的背影，我的泪很快地流下来了。我赶紧拭干了泪。怕他看见，也怕别人看见。我再向外看时，他已抱了朱红的橘子往回走了。过铁道时，他先将橘子散放在地上，自己慢慢爬下，再抱起橘子走。到这边时，我赶紧去搀他。他和我走到车上，将橘子一股脑儿放在我的皮大衣上。于是扑扑衣上的泥土，心里很轻松似的。过一会儿说：“我走了，到那边来信！”我望着他走出去。他走了几步，回过头看见我，说：“进去吧，里边没人。”等他的背影混入来来往往的人里，再找不着了，我便进来坐下，我的眼泪又来了。", "—— 朱自清《背影》"));
        rememberAdapter = new RememberTextAdapter(rememberTexts);

        List<Collection> collections = new ArrayList<>();
        collections.add(new CollectionParent(R.id.parent_type, 0, 0, "古诗词"));
        collections.add(new CollectionChild(R.id.child_type, 0, 1, "苟利国家生死以，岂因福祸避趋之。"));
        collections.add(new CollectionChild(R.id.child_type, 0, 2, "苟全性命于乱世，不求闻达于诸侯。"));
        collections.add(new CollectionChild(R.id.child_type, 0, 3, "我有一壶酒，足以慰风尘。"));
        collections.add(new CollectionParent(R.id.parent_type, 1, 0, "文言文"));
        collections.add(new CollectionChild(R.id.child_type, 1, 1, "苟富贵，毋相忘。"));
        collections.add(new CollectionChild(R.id.child_type, 1, 2, "王侯将相宁有种乎？"));
        collections.add(new CollectionParent(R.id.parent_type, 2, 0, "现代文"));
        collections.add(new CollectionChild(R.id.child_type, 2, 1, "我去买几个橘子，你就站在此地，不要走动。"));
        List<Integer> shownPositionList = new ArrayList<>();
        shownPositionList.add(0);
        shownPositionList.add(1);
        shownPositionList.add(2);
        shownPositionList.add(3);
        shownPositionList.add(4);
        shownPositionList.add(5);
        shownPositionList.add(6);
        shownPositionList.add(7);
        shownPositionList.add(8);
        List<Integer> parentPositionList = new ArrayList<>();
        parentPositionList.add(0);
        parentPositionList.add(4);
        parentPositionList.add(7);
        List<Integer> parentShownPositionList = new ArrayList<>();
        parentShownPositionList.add(0);
        parentShownPositionList.add(4);
        parentShownPositionList.add(7);
        collectAdapter = new CollectionAdapter(collections, shownPositionList, parentPositionList, parentShownPositionList);

    }

    public static ViewPagerFragment newInstance(int sectionNumber) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        RecyclerView recyclerView;
        if (getArguments() != null)
            switch (getArguments().getInt("section_number")) {
                case 0:
                    rootView = inflater.inflate(R.layout.content_main_view_pager_recite, container, false);
                    Configuration configuration = this.getResources().getConfiguration();
                    recyclerView = rootView.findViewById(R.id.first_recycler_view);
                    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
                    } else {
                        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
                    }
                    recyclerView.setAdapter(reciteAdapter);
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.content_main_view_pager_remember, container, false);
                    recyclerView = rootView.findViewById(R.id.second_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    recyclerView.setAdapter(rememberAdapter);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.content_main_view_pager_collect, container, false);
                    recyclerView = rootView.findViewById(R.id.third_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    recyclerView.setAdapter(collectAdapter);
                    break;
            }
        return rootView;
    }

}
