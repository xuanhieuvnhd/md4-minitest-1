package com.service.spending;

import com.model.Spending;
import com.service.IGeneralService;

import java.util.List;

public interface ISpendingService extends IGeneralService<Spending> {
    List<Spending> findAllSpending();
}
