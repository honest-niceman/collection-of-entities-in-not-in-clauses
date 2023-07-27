package com.example.demo;

import com.example.demo.entity.Specialty;
import com.example.demo.entity.Vet;
import com.example.demo.repo.SpecialtyRepository;
import com.example.demo.repo.VetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final SpecialtyRepository specialtyRepository;
    private final VetRepository vetRepository;

    public DemoApplication(SpecialtyRepository specialtyRepository,
                           VetRepository vetRepository) {
        this.specialtyRepository = specialtyRepository;
        this.vetRepository = vetRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Set<Specialty> specialties = new HashSet<>();
        specialties.add(new Specialty());
        specialties.add(new Specialty());
        specialties.add(new Specialty());
        specialties.add(new Specialty());

        specialtyRepository.saveAll(specialties);

        Vet vet = new Vet();

        specialties.forEach(vet::addSpecialty);

        vetRepository.save(vet);

        vetRepository.findBySpecialtiesIn(specialties);
    }
}
