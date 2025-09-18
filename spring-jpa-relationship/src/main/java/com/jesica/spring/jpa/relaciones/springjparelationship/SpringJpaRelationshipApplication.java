package com.jesica.spring.jpa.relaciones.springjparelationship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jesica.spring.jpa.relaciones.springjparelationship.entities.Address;
import com.jesica.spring.jpa.relaciones.springjparelationship.entities.Client;
import com.jesica.spring.jpa.relaciones.springjparelationship.entities.Invoice;
import com.jesica.spring.jpa.relaciones.springjparelationship.repositories.ClientRepository;
import com.jesica.spring.jpa.relaciones.springjparelationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOne();
		// manyToOneFindByIdClient();
		// OneToMany();
		// oneToManyFindById();
		//removeAddress();
		oneToManyInvoiceBidireccional();

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById(){
		Optional<Client>optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			
		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

		//Opcion 2
		client.addInvoice(invoice1).addInvoice(invoice2);
		//Opcion 1

		clientRepository.save(client);
		System.out.println(client);

		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccional(){
		Client client = new Client("Fran", "Moras");
		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

		//Opcion 2
		client.addInvoice(invoice1).addInvoice(invoice2);
		//Opcion 1
		/*
		List<Invoice>invoices = new ArrayList<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);
		*/
		invoice1.setCliente(client);
		invoice2.setCliente(client);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Avenida Rivadavia", 6000);
			Address address2 = new Address("San Justo", 7000);

			client.setAddresses(Arrays.asList(address1, address2));
			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Maria", "Simpson");

		Address address1 = new Address("Avenida Rivadavia", 6000);
		Address address2 = new Address("San Justo", 7000);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	/*
	 * Codificando con OneToMany con datos existentes y acoplando la fk de cliente
	 * en Address
	 */
	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Avenida Rivadavia", 6000);
			Address address2 = new Address("San Justo", 7000);

			// aqui trae solo cliente y la direccion lo va a traer de
			// forma perezosa con tipo fetch lazy
			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);
			/*
			 * persiste 1ro al cliente y las 2 direcciones
			 * va asisgna la fk a cada direccion y el client id =null
			 * luego actualiza la bbd de la fk con el id del cliente
			 * y emprimimos
			 */
			System.out.println(client);

		});
	}

	@Transactional
	public void OneToMany() {
		Client client = new Client("Maria", "Simpson");

		Address address1 = new Address("Avenida Rivadavia", 6000);
		Address address2 = new Address("San Justo", 7000);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("Belen", "Salva");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setCliente(client);

		Invoice invoicedb = invoiceRepository.save(invoice);
		System.out.println(invoicedb);
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client cliente = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("compras de fiestas", 3000L);
			invoice.setCliente(cliente);

			Invoice invoicedb = invoiceRepository.save(invoice);
			System.out.println(invoicedb);
		}
	}

}
