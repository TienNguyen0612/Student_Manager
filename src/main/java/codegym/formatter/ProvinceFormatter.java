package codegym.formatter;

import codegym.model.Province;
import codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.util.Locale;

public class ProvinceFormatter implements Formatter<Province> {
    private final IProvinceService iprovinceService;

    @Autowired
    public ProvinceFormatter(IProvinceService iprovinceService) {
        this.iprovinceService = iprovinceService;
    }

    @Override
    public Province parse(String text, Locale locale) {
        return iprovinceService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getP_id() + ", " +object.getP_name() + "]";
    }
}
