package com.nine96kibs.nine96kibsandroid.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nine96kibs.nine96kibsandroid.R;
import com.nine96kibs.nine96kibsandroid.collect.Collection;
import com.nine96kibs.nine96kibsandroid.collect.CollectionAdapter;
import com.nine96kibs.nine96kibsandroid.collect.CollectionChild;
import com.nine96kibs.nine96kibsandroid.collect.CollectionParent;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTask;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTaskAdapter;
import com.nine96kibs.nine96kibsandroid.remember.RememberText;
import com.nine96kibs.nine96kibsandroid.remember.RememberTextAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends Fragment {

    RecyclerView.Adapter reciteAdapter;
    RecyclerView.Adapter rememberAdapter;
    RecyclerView.Adapter collectAdapter;

    public ViewPagerFragment() {
        List<ReciteTask> reciteTasks = new ArrayList<>();
        reciteTasks.add(new ReciteTask("test", 4, 3, 2));
        reciteTasks.add(new ReciteTask("520", 5, 3, 0));
        reciteTasks.add(new ReciteTask("666", 6, 6, 6));
        reciteTasks.add(new ReciteTask("test", 4, 3, 2));
        reciteTasks.add(new ReciteTask("520", 5, 3, 0));
        reciteTasks.add(new ReciteTask("666", 6, 6, 6));
        reciteTasks.add(new ReciteTask("test", 4, 3, 2));
        reciteTasks.add(new ReciteTask("520", 5, 3, 0));
        reciteTasks.add(new ReciteTask("666", 6, 6, 6));
        reciteAdapter = new ReciteTaskAdapter(reciteTasks);

        List<RememberText> rememberTexts = new ArrayList<>();
        rememberTexts.add(new RememberText("青烟幂处，碧海飞金镜。永夜闲阶卧桂影。\n露凉时、零乱多少寒螀，神京远，惟有蓝桥路近。\n" +
                "水晶帘不下，云母屏开，冷浸佳人淡脂粉。\n待都将许多明，付与金尊，投晓共、流霞倾尽。\n更携取、胡床上南楼，看玉做人间，素秋千顷。", "——《洞仙歌·泗州中秋作》"));
        rememberTexts.add(new RememberText("\u3000\u3000" + "我说道：“爸爸，你走吧。”他望车外看了看，说：“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。我本来要去的，他不肯，只好让他去。我看见他戴着黑布小帽，穿着黑布大马褂12，深青布棉袍，蹒跚13地走到铁道边，慢慢探身下去，尚不大难。可是他穿过铁道，要爬上那边月台，就不容易了。他用两手攀着上面，两脚再向上缩；他肥胖的身子向左微倾，显出努力的样子。这时我看见他的背影，我的泪很快地流下来了。我赶紧拭干了泪。怕他看见，也怕别人看见。我再向外看时，他已抱了朱红的橘子往回走了。过铁道时，他先将橘子散放在地上，自己慢慢爬下，再抱起橘子走。到这边时，我赶紧去搀他。他和我走到车上，将橘子一股脑儿放在我的皮大衣上。于是扑扑衣上的泥土，心里很轻松似的。过一会儿说：“我走了，到那边来信！”我望着他走出去。他走了几步，回过头看见我，说：“进去吧，里边没人。”等他的背影混入来来往往的人里，再找不着了，我便进来坐下，我的眼泪又来了。", "——朱自清《背影》"));
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
