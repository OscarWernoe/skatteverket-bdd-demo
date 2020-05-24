package com.example.demo.service;

import com.example.demo.domain.IU;
import com.example.demo.domain.Status;
import com.example.demo.repository.IURepository;
import org.springframework.stereotype.Service;

@Service
public class IUService {

    private final IURepository iuRepository;


    public IUService(IURepository iuRepository) {
        this.iuRepository = iuRepository;
    }

    public void processIU(IU iu) {
        //1. format
        //2. samband
        //3 save or log
    }

    private void saveIUAsInkommen(IU iu) {
        iu.setStatus(Status.INKOMMEN);
        iuRepository.save(iu);
    }

    private void loggFailedIU(IU iu) {

    }

}
