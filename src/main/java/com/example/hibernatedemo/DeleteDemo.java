package com.example.hibernatedemo;

import com.example.entity.Instructor;
import com.example.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();
            int id=1;

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);

         //   System.out.println("tempInstructorDetail"+ tempInstructorDetail);

      //      System.out.println("THE Associated instructor" +tempInstructorDetail.getInstructor());

            //remove associated reference , break the bi relation
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);
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
