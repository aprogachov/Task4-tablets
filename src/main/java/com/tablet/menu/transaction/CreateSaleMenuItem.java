package com.tablet.menu.transaction;


import com.modelsale.model.Patient;
import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IPatientRepository;
import com.tablet.repository.domain.IProductRepository;
import com.tablet.repository.domain.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SaleMenuItem
public class CreateSaleMenuItem implements IMenuItem {

    private final IPatientRepository ipatientRepository;
    private final IProductRepository iproductRepository;
    private final ITransactionRepository itransactionRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public CreateSaleMenuItem(
            IPatientRepository ipatientRepository,
            IProductRepository iproductRepository,
            ITransactionRepository itransactionRepository,
            MenuHelper menuHelper) {
        this.ipatientRepository = ipatientRepository;
        this.iproductRepository = iproductRepository;
        this.itransactionRepository = itransactionRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Make sale";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("input patient phone: ");
        String phone = menuHelper.read();

        Patient patient = ipatientRepository.findByPhone(phone);
        if (patient == null) {
            System.out.println("Patient not found");
            return 0;
        }

        System.out.println("input product name");
        String name = menuHelper.read();

        Product product = iproductRepository.findByName(name);
        if (product == null) {
            System.out.println("product not found");
            return 0;
        }

        try {
            itransactionRepository.sale(product, patient);
        } catch (Exception e) {
            System.out.println("failed to complete transaction");
            e.printStackTrace();
        }

        return 0;
    }
}
