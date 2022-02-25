package codegym.service.impl;

import codegym.model.Province;
import codegym.repository.IProvinceRepository;
import codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProvinceServiceImpl implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public ArrayList<Province> findAll() {
        return (ArrayList<Province>) provinceRepository.findAll();
    }

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public void delete(int id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public Province findById(int id) {
       if (provinceRepository.findById(id).isPresent()) {
           return provinceRepository.findById(id).get();
       }
       return null;
    }
}
