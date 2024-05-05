package com.riwi.vacants.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.Entity.Company;
import com.riwi.vacants.Entity.Vacant;
import com.riwi.vacants.Repository.CompanyRepository;
import com.riwi.vacants.Repository.VacantRepository;
import com.riwi.vacants.Services.interfaces.IVacantService;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.CompanyToVacantResponse;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import com.riwi.vacants.utils.enums.StateVacant;
import com.riwi.vacants.utils.exceptions.idNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantService{

    @Autowired
    private final VacantRepository objVacantRepository;
    @Autowired
    private final CompanyRepository objCompanyRepository;

    @Override
    public void delete(Long id) {
        // Busco
        Vacant vacant = this.find(id);
        // Elimino
        this.objVacantRepository.delete(vacant);
    }

    @Override
    public VacantResponse create(VacantRequest request) {
        Company company = this.objCompanyRepository.findById(request.getCompanyId()).orElseThrow(() -> new idNotFoundException("company"));

                // Convertimos el request a la entidad
        Vacant vacant = this.requestToVacant(request, new Vacant());

        // Agregamos la empresa encontrada anteriormente
        vacant.setCompany(company);

        return this.entityToResponse(this.objVacantRepository.save(vacant));
    }

    @Override
    public VacantResponse update(Long id, VacantRequest request) {
        Vacant vacant = this.find(id);

        Company company = this.objCompanyRepository.findById(request.getCompanyId())
        .orElseThrow(()-> new idNotFoundException("Company"));
        vacant = this.requestToVacant(request, vacant);

        vacant.setCompany(company);
        if(request.getStatus() != null)
        vacant.setStatus(request.getStatus());

        return this.entityToResponse(this.objVacantRepository.save(vacant));

    }
   
    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if(page < 0)
        page = 0;

        // Creamos la paginacion
        PageRequest pagination = PageRequest.of(page, size);

        // Obtenemos todas las vacantes de la BD y las mapeamos en el dto de respuesta
        return this.objVacantRepository.findAll(pagination).map(vacant -> this.entityToResponse(vacant));
    }

    @Override
    public VacantResponse getById(Long id) {
       return this.entityToResponse(this.find(id));
    }



    private VacantResponse entityToResponse(Vacant vac){
        // Crear la instancia de la respuesta
        VacantResponse response = new VacantResponse();
        // Copiamos las propiedades de la entidad al dto de respuesta
        BeanUtils.copyProperties(vac, response);
        // Crear la instancia de dto de compaia dentro vacante
        CompanyToVacantResponse objCompanyToVacantResponse = new CompanyToVacantResponse();


        // copio las propiedades de la entidad en el dto de respuesta

        BeanUtils.copyProperties(vac.getCompany(), objCompanyToVacantResponse);

        // Agregamos el dto de respuesta de la compañia en la respuesta general

        response.setCompany(objCompanyToVacantResponse);

        return response;

    }
    private Vacant requestToVacant(VacantRequest vacantRequest, Vacant entity){

        entity.setDescription(vacantRequest.getDescription());
        entity.setTitle(vacantRequest.getTitle());
        entity.setStatus(StateVacant.ACTIVE);

        return entity;

    }
    private Vacant find(Long id){
        return this.objVacantRepository.findById(id).orElseThrow(()-> new idNotFoundException("vacant"));
    }
}
