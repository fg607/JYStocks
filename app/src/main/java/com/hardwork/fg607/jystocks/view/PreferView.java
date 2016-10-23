package com.hardwork.fg607.jystocks.view;

import com.hardwork.fg607.jystocks.model.SHCData;

import java.util.List;

/**
 * Created by fg607 on 16-9-7.
 */
public interface PreferView extends BaseView {

    public void updateSHC(List<SHCData> shcDataList);
}
