package kata.sg.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kata.sg.entities.Client;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	private static Client client;

	@BeforeEach
	void setUp() {
		client = new Client();
		client.setLastName("NSIRI");
	}

	@Test
	public void saveClientAndFindById() {
		Client savedClient = this.clientRepository.save(client);
		Optional<Client> foundClient = this.clientRepository.findById(savedClient.getId());

		assertTrue(foundClient.isPresent());
		assertEquals(foundClient.get(), client);
	}

	@Test
	public void saveClientAndFindByName() {
		Client savedClient = this.clientRepository.save(client);
		List<Client> clients = this.clientRepository.findByLastName(savedClient.getLastName());
		assertEquals(clients.get(0).getLastName(), "NSIRI");
	}
}