package edu.icesi.pensamiento_computacional.mocked;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import edu.icesi.pensamiento_computacional.model.Enrollment;
import edu.icesi.pensamiento_computacional.repository.IEnrollmentRepository;
import edu.icesi.pensamiento_computacional.services.IEnrollmentService;
import jakarta.xml.bind.annotation.W3CDomHandler;

@SpringBootTest
@ActiveProfiles("test")
public class EnrollmentRepositoryTest {

   @Autowired
   private IEnrollmentService enrollmentService;

   @MockBean
   private IEnrollmentRepository enrollmentRepository;

   	@Test
	void contextLoads() {
	}


   @Test 
   void findByTermTest(){

    //Arrange
    Integer termId = 123;
    Enrollment e = new Enrollment();
    e.setId(1);
    e.setEnrolledOn(LocalDateTime.now());
    
    when(enrollmentRepository.findByTerm_Id(termId)).thenReturn(List.of(e));

    var result = enrollmentService.findByTerm(termId);

    assertNotNull(result);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());

    verify(enrollmentRepository, times(1)).findByTerm_Id(termId);
    verifyNoMoreInteractions(enrollmentRepository);


   }
}
