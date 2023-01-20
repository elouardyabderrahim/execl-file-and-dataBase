package com.exportImportexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExportImportexcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExportImportexcelApplication.class, args);
    }
//	@Bean
//	public CommandLineRunner demoData(EmployeeRepository repo) {
//		return args -> {
//			for(int i=0;i<4;i++){
//			Employee p=new Employee();
//			p.setFirstName(RandomString.make(5));
//			p.setSalary(20000);
//			p.setLastName(RandomString.make(6));
//			p.setStartsAt(new Date());
//			p.setBirthDay(new Date());
//
//			repo.save(p);}
//		};
}


