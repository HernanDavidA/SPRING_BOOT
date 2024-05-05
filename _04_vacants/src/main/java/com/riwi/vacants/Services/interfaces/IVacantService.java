package com.riwi.vacants.Services.interfaces;

import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.VacantResponse;

public interface IVacantService extends CRUDService<VacantRequest, VacantResponse, Long>{

    public VacantResponse getById(Long id);

    


}
