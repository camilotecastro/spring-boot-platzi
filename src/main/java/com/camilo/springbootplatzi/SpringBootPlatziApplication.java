package com.camilo.springbootplatzi;

import com.camilo.springbootplatzi.bean.MyBean;
import com.camilo.springbootplatzi.bean.MyBeanWithDependency;
import com.camilo.springbootplatzi.component.ComponentDependency;
import com.camilo.springbootplatzi.configuration.MyBeanWithProperties;
import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.pojo.UserPojo;
import com.camilo.springbootplatzi.repository.UserRepository;
import com.camilo.springbootplatzi.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootPlatziApplication implements CommandLineRunner {
    private final Log LOGGER = LogFactory.getLog(SpringBootPlatziApplication.class);
    private final ComponentDependency componentDependency;
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;
    private final MyBeanWithProperties myBeanWithProperties;
    private final UserPojo userPojo;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public SpringBootPlatziApplication(
            @Qualifier("componenteTwoImplement") ComponentDependency componentDependency,
            MyBean myBean,
            MyBeanWithDependency myBeanWithDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo, UserRepository userRepository,
            UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlatziApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       //ejemplosAnteriores();
       saveUsersInDataBase();
       //getInformationJpqlFromUser();
        //saveWithErrorTransactional();
    }

    private void saveWithErrorTransactional(){
        User test1 = new User("test1", "test1@gmail.com",LocalDate.of(1997,1,6));
        User test2 = new User("test2", "test2@gmail.com",LocalDate.of(1997,1,6));
        User test3 = new User("test3", "test1@gmail.com",LocalDate.of(1997,1,6));
        List<User> users = Arrays.asList(test1,test2,test3);
        try {
            userService.saveTransactional(users);
        } catch (Exception e){
            LOGGER.error("Exception dentro del metodo saveWithErrorTransactional");
        }
        userService
                .getAllUsers()
                .stream().forEach(System.out::println);

    }

    public void getInformationJpqlFromUser(){
        String email = "jhon1@gmail.com";
        String name = "Jhon";
        // Mostramos la informaci??n con un log
        LOGGER.info("Usuario con el metodo findByUserEmail: " +
                userRepository
                        .findByUserEmail(email)
                        .orElseThrow(()->
                                new RuntimeException("No se encontro ningun usuario con el email: " + email)));

        userRepository
                .findAndSort(name, Sort.by("id")
                .descending())
                .forEach(LOGGER::info);

        userRepository
                .findByNameAndEmail(name,email)
                .orElseThrow(()->
                        new RuntimeException("No se encontro el usuarion con el nombre: " + name+" y el email: " + email));
        userRepository.findByName(name)
                .forEach(LOGGER::info);

        LOGGER.info("El usuarioDTO apartir del named parameter es: " +
                userRepository.getAllByBirthDateAndEmail
                                (LocalDate.of(2021,03,1),"jhon1@gmail.com")
                .orElseThrow(()->
                        new RuntimeException("No se encontro el usuario apartir del named parameter")));

    }

    public void saveUsersInDataBase(){
        User user = new User("Jhon","jhon1@gmail.com", LocalDate.of(2021,03,1));
        User user1 = new User("Julie","Julie@gmail.com", LocalDate.of(2021,11,1));
        User user2 = new User("Daniela","Daniela@gmail.com", LocalDate.of(2021,12,2));
        User user14 = new User("Daniela","Daniela14@gmail.com", LocalDate.of(2021,12,2));
        User user3 = new User("user3","user3@gmail.com", LocalDate.of(2021,03,4));
        User user4 = new User("user4","user4@gmail.com", LocalDate.of(2021,03,6));
        User user5 = new User("user5","user5@gmail.com", LocalDate.of(2021,04,11));
        User user6 = new User("user6","user6@gmail.com", LocalDate.of(2021,04,30));
        User user7 = new User("user7","jhuser7on@gmail.com", LocalDate.of(2021,05,11));
        User user8 = new User("user8","user8@gmail.com", LocalDate.of(2021,03,21));
        User user9 = new User("user9","jhuser9on@gmail.com", LocalDate.of(2021,05,23));
        User user10 = new User("user10","user10@gmail.com", LocalDate.of(2021,06,24));
        User user11 = new User("user10","jhuser10on@gmail.com", LocalDate.of(2021,06,24));
        User user12 = new User("user12","user12@gmail.com", LocalDate.of(2021,07,11));

        List<User> users = Arrays.asList(
                user,user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12, user14
        );
        users.forEach(userRepository::save);

    }

    public void ejemplosAnteriores(){
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());
        try {
            int num = 10/0;
            LOGGER.debug("Mi numero " + num);
        }catch (Exception e){
            LOGGER.error("Division por 0 cero");
        }
    }
}
