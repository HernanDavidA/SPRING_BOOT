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
import com.riwi.vacants.utils.exceptions.idNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

    @Autowired
    private final CompanyRepository objCompanyRepository;
    @Override
    public void delete(String id) {

        // Buscar la compañia a la que corresponde el id
       Company company = this.find(id);
       // Elimino el objeto
       this.objCompanyRepository.delete(company);
    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
       Company company = this.requestToCompany(request, new Company());

        return this.entityTCompanyResponse(this.objCompanyRepository.save(company));

    }

    @Override
    public CompanyResponse update(String id, CompanyRequest resquest) {
        // Buscamos la compañia por ID
        Company company = this.find(id);

        // Llenamos la compañia con los nuevos datos a la que vez que lo convertimos en entidad
        Company companyUpdate = this.requestToCompany(resquest, company);
    
        //Guardamos la compañia actualizada y convertimos en respuesta
        return this.entityTCompanyResponse(this.objCompanyRepository.save(companyUpdate));
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

        return this.objCompanyRepository.findById(id).orElseThrow(()-> new idNotFoundException("company"));
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
