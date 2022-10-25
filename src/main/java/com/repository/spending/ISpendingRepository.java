package com.repository.spending;

import com.model.Spending;
import com.repository.IGeneralRepository;

import java.util.List;

public interface ISpendingRepository extends IGeneralRepository<Spending> {

    List<Spending> findAll();
}
