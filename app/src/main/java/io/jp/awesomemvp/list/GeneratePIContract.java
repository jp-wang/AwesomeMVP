package io.jp.awesomemvp.list;

import java.util.List;

import io.jp.mvp.IPresenter;
import io.jp.mvp.IView;

interface GeneratePIContract {
    interface IGeneratePIView extends IView {
        void updatePIList(List<String> PIs);
    }

    interface IGeneratePIPresenter extends IPresenter<IGeneratePIView, GeneratePIModel> {
        void generatePIs();
    }
}
