package com.ernok.stacktest01.util;

import com.ernok.stacktest01.models.Person;
import com.ernok.stacktest01.models.Project;
import com.ernok.stacktest01.models.Workplace;
import com.ernok.stacktest01.repositories.PeopleRepo;
import com.ernok.stacktest01.repositories.ProjectRepo;
import com.ernok.stacktest01.repositories.WorkplaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Runner method for playing around with the database:
// adds entries in database tables

@Component
public class DatabaseRunner implements CommandLineRunner {

    private final PeopleRepo peopleRepo;
    private final ProjectRepo projectRepo;
    private final WorkplaceRepo workplaceRepo;

    @Autowired
    public DatabaseRunner(PeopleRepo peopleRepo,
                          ProjectRepo projectRepo,
                          WorkplaceRepo workplaceRepo) {
        this.peopleRepo = peopleRepo;
        this.projectRepo = projectRepo;
        this.workplaceRepo = workplaceRepo;
    }

    @Override
    public void run(String... args) {

        projectRepo.deleteAllInBatch();
        peopleRepo.deleteAllInBatch();
        workplaceRepo.deleteAllInBatch();

        Person ville = new Person(
            "Ville",
            "Vallaton",
            "ville@email.com",
            1234
        );

        Person kalle = new Person(
            "Kalle",
            "Kallela",
            "kalle@email.com",
            5678
        );

        Person seppo = new Person(
            "Seppo",
            "Seppälä",
            "seppo@email.com",
            9987
        );

        Project proju1 = new Project(
            "Projekti1",
            LocalDate.of(2018, 1, 1),
            LocalDate.of(2019, 1, 1)
        );

        Project proju2 = new Project(
            "Projekti2",
            LocalDate.of(2020, 5, 5),
            LocalDate.of(2025, 9, 9)
        );

        Workplace tyopaikka1 = new Workplace(
            "Työpaikka 1"
        );

        Workplace tyopaikka2 = new Workplace(
            "Työpaikka 2"
        );

        Workplace tyopaikka3 = new Workplace(
            "TestiPaikka"
        );

        workplaceRepo.save(tyopaikka1);
        workplaceRepo.save(tyopaikka2);
        workplaceRepo.save(tyopaikka3);

        proju1.getPeople().add(ville);
        ville.getProjects().add(proju1);

        proju1.getPeople().add(kalle);
        kalle.getProjects().add(proju1);

        proju2.getPeople().add(ville);
        ville.getProjects().add(proju2);

        proju2.getPeople().add(seppo);

        ville.setWorkplace(tyopaikka1);
        kalle.setWorkplace(tyopaikka3);
        seppo.setWorkplace(tyopaikka3);

        peopleRepo.save(ville);
        peopleRepo.save(kalle);
        peopleRepo.save(seppo);

        System.err.println("---- Database ready ----");
    }

}
