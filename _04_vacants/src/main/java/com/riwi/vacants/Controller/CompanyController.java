package com.riwi.vacants.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacants.Services.interfaces.ICompanyService;
import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    @Autowired
    private final ICompanyService oICompanyService;
    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> get(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size
    ){
        return ResponseEntity.ok(this.oICompanyService.getAll(page - 1, size));
    }
    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody CompanyRequest companyRequest){
        return ResponseEntity.ok(this.oICompanyService.create(companyRequest));
    }
    

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.oICompanyService.getById(id));
    }
    
}
