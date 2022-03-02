package com.example.hibernatedemo;

//import org.springframework.boot.SpringApplication;
import com.example.entity.Instructor;
import com.example.entity.InstructorDetail;
import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.cfg.Configuration;


//@SpringBootApplication
public class HibernatedemoApplication {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session =factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();
            int id=10;

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);

            System.out.println("tempInstructorDetail"+ tempInstructorDetail);

            System.out.println("THE Associated instructor" +tempInstructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");


        } catch (Exception exc){

            exc.printStackTrace();

        }

        finally {
            //handling leak exception
            session.close();
            factory.close();

        }
    }
}
