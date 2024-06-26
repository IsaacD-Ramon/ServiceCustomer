package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository repository;

    public List<CustomerDTO>  findAlllCustomers(){
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        repository.findAll().stream().forEach(item->{
            customerDTOS.add(mapCustomerEntityToDTO(item));
        });

        return customerDTOS;
    }

    public void createNewCustomer(CustomerDTO customerDTO){
        repository.persist(mapCustomerDTOToEntity(customerDTO));
    }

    public void changeCustomer(Long id, CustomerDTO customerDTO){

        CustomerEntity customer = repository.findById(id);
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setAge(customerDTO.getAge());
        customer.setName(customerDTO.getName());

        repository.persist(customer);
    }

    public void deleteCustomer(Long id){

        repository.deleteById(id);
    }

    private CustomerEntity mapCustomerDTOToEntity(CustomerDTO customerDTO){
        CustomerEntity customer = new CustomerEntity();

        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setAge(customerDTO.getAge());
        customer.setName(customerDTO.getName());

        return  customer;
    }
    private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customerEntity){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setAge(customerEntity.getAge());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setEmail(customerEntity.getEmail());

        return customerDTO;
    }

    public CustomerDTO findCustomerById(Long id) {
        return mapCustomerEntityToDTO(repository.findById(id));
    }
}
