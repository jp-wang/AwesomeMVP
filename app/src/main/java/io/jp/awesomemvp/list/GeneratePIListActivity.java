package io.jp.awesomemvp.list;

import android.os.Bundle;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.jp.awesomemvp.R;
import io.jp.mvp.BaseActivity;

public class GeneratePIListActivity extends BaseActivity<GeneratePIModel, GeneratePIContract.IGeneratePIView, GeneratePIContract.IGeneratePIPresenter> implements GeneratePIContract.IGeneratePIView {

    private ListAdapter<String, PIListAdapter.ViewHolder> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pi_list);
        setupListAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        getPresenter().generatePIs();
    }

    private void setupListAdapter() {
        listAdapter = new PIListAdapter();
        RecyclerView recyclerView = this.findViewById(R.id.pi_list);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void updatePIList(List<String> PIs) {
        listAdapter.submitList(PIs);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected GeneratePIContract.IGeneratePIPresenter createPresenter() {
        return new GeneratePIPresenter();
    }

    @Override
    protected GeneratePIModel createModel() {
        return new GeneratePIModel();
    }
}
