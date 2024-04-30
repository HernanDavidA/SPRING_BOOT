package com.riwi.vacants.Services.interfaces;

import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.response.CompanyResponse;

public interface ICompanyService extends CRUDService<CompanyRequest, CompanyResponse, String>{

    public CompanyResponse getById(String id);

}
