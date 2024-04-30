package com.riwi.vacants.Services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.Entity.Company;
import com.riwi.vacants.Entity.Vacant;
import com.riwi.vacants.Repository.CompanyRepository;
import com.riwi.vacants.Services.interfaces.ICompanyService;
import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.response.CompanyResponse;
import com.riwi.vacants.utils.dto.response.VacantToCompanyResponse;

import lombok.AllArgsConstructor;
import lombok.var;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

    @Autowired
    private final CompanyRepository objCompanyRepository;
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
       Company company = this.requestToCompany(request, new Company());

        return this.entityTCompanyResponse(this.objCompanyRepository.save(company));

    }

    @Override
    public CompanyResponse update(CompanyRequest resquest, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Page<CompanyResponse> getAll(int page, int size) {
        if (page<0) 
            page = 0;
            PageRequest pagination = PageRequest.of(page, size);

            // Iteramos con map cada compañia y la convertimos 
            // podemos hacerlo con la expresion lambda flecha ->
            // o expresion lambda diferencial :: 
            // return this.objCompanyRepository.findAll(pagination).map(company -> this.entityTCompanyResponse(company));

            return this.objCompanyRepository.findAll(pagination).map(this::entityTCompanyResponse);
    }
    @Override
    public CompanyResponse getById(String id) {
       return this.entityTCompanyResponse(this.find(id));
    }



    private Company find(String id){
        return this.objCompanyRepository.findById(id).orElseThrow();
    }



    // Este metodo se encargar de convertir un objeto Company a CompanyResponse
    private CompanyResponse entityTCompanyResponse(Company entity){
        CompanyResponse response = new CompanyResponse();

        /*
         * Mapeamos las vacantes convirtiendo cada una de ellas a al dto de response 
         * stream() convierte una lista en una coleccion para poder acceder a los map, for each etc.
         * 
         *.collect(Collectros.toList()) convierte la coleccion de nuevo a lista
         *
         */
        response.setVacants(entity.getVacants().stream().map(vacant -> this.vacantToCompanyResponse(vacant)).collect(Collectors.toList()));
        
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    private VacantToCompanyResponse vacantToCompanyResponse(Vacant entity){
        VacantToCompanyResponse response = new VacantToCompanyResponse();

        BeanUtils.copyProperties(entity, response);

        return response;
    }
    private Company requestToCompany(CompanyRequest request, Company company){
        BeanUtils.copyProperties(request, company);
        company.setVacants(new ArrayList<>());
        return company;
    }

}
