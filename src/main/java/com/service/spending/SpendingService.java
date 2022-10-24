package com.service.spending;

import com.model.Spending;
import com.repository.spending.ISpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SpendingService implements ISpendingService {

    @Autowired
    ISpendingRepository spendingRepository;
    @Override
    public List<Spending> findAll() {
        return spendingRepository.findAll();
    }

    @Override
    public Spending findById(Long id) {
        return spendingRepository.findById(id);
    }

    @Override
    public void save(Spending spending) {
        spendingRepository.save(spending);
    }

    @Override
    public void delete(Long id) {
        spendingRepository.delete(id);
    }

    @Override
    public List<Spending> findByName(String name) {
        name = "%" + name + "%";
        return spendingRepository.findByName(name);
    }
}
