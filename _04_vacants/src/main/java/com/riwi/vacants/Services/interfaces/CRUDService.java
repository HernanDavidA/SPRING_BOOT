package com.riwi.vacants.Services.interfaces;

import org.springframework.data.domain.Page;

public interface CRUDService<RQ, RS, ID> {

    public void delete(ID id);
    public RS create(RQ request);
    public RS update(ID id, RQ resquest);
    public Page<RS> getAll(int page, int size);
}
