package cl.duoc.bff.controller;

import cl.duoc.bff.model.Account;
import cl.duoc.bff.service.BankDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BFF Web - returns full account info
 */
@RestController
public class WebBffController {
    private final BankDataService svc;
    public WebBffController(BankDataService svc){this.svc = svc;}

    @GetMapping("/web/accounts")
    public List<Account> accounts(){ return svc.getAllAccounts(); }

    @GetMapping("/web/accounts/{accountNumber}")
    public Account account(@PathVariable String accountNumber){ return svc.getAccountById(accountNumber); }
}
