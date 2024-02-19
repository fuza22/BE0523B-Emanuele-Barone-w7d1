package it.epicode.w7d1.service;

import it.epicode.w7d1.enums.Disponibilita;
import it.epicode.w7d1.exception.AlreadyAssignedException;
import it.epicode.w7d1.exception.MaintenanceException;
import it.epicode.w7d1.exception.NotFoundException;
import it.epicode.w7d1.model.Dipendente;
import it.epicode.w7d1.model.Dispositivo;
import it.epicode.w7d1.repository.DispositivoRepository;
import it.epicode.w7d1.request.DispositivoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAll(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispositivoById(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).orElseThrow(()->new NotFoundException("Dispositivo con id = " + id + " non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest){
        Dispositivo d = new Dispositivo();
        d.setDisponibilita(dispositivoRequest.getDisponibilita());
        d.setTipologia(dispositivoRequest.getTipologia());

        return dispositivoRepository.save(d);
    }

    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException {
        Dispositivo d = new Dispositivo();
        d.setDipendente(dispositivoRequest.getDipendente());
        d.setDisponibilita(dispositivoRequest.getDisponibilita());
        d.setTipologia(dispositivoRequest.getTipologia());



        return dispositivoRepository.save(d);
    }

    public void deleteDispositivo(int id) throws NotFoundException {
        Dispositivo d = getDispositivoById(id);
        dispositivoRepository.delete(d);
    }

    public Dispositivo assegnaDipendente(int idDispositivo, int idDipendente) throws NotFoundException{

        Dipendente dipendente = dipendenteService.getDipendenteById(idDipendente);
        Dispositivo dispositivo = getDispositivoById(idDispositivo);

        if(dispositivo.getDisponibilita() == Disponibilita.ASSEGNATO){

            throw new AlreadyAssignedException("Il dispositivo è già assegnato ad un dipendente");

        }
        if(dispositivo.getDisponibilita() == Disponibilita.IN_MANUTENZIONE){

            throw new MaintenanceException("Il dispositivo è attualmente in manutenzione");

        }

        dispositivo.setDipendente(dipendente);
        dispositivo.setDisponibilita(Disponibilita.ASSEGNATO);
        return dispositivoRepository.save(dispositivo);

    }

}
