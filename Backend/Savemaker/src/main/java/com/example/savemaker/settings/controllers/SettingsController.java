package com.example.savemaker.settings.controllers;

import com.example.savemaker.balance.models.IncomeTypeBalance;
import com.example.savemaker.balance.services.BalanceService;
import com.example.savemaker.settings.dtos.PriorityListElementDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/settings")
public class SettingsController {

    private final BalanceService balanceService;

    public SettingsController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping(value = "/prioritylist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriorityListElementDTO>> getPriorityList() {
        List<IncomeTypeBalance> balances = balanceService.getAllIncomeTypeBalances();
        List<PriorityListElementDTO> dtos = balances.stream().map(v -> new PriorityListElementDTO(v)).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping(value = "/prioritylist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePriorityList(@RequestBody List<PriorityListElementDTO> newPriorityList) {
        Boolean result = balanceService.updatePriorityList(newPriorityList);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
