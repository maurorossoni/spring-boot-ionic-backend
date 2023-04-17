package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.resources.exception.FieldMessage;
import com.nelioalves.cursomc.services.validation.utils.BR;
import com.nelioalves.cursomc_dto.ClienteNewDTO;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

       if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.gedCod()) && !BR.IsValidCPF(objDto.getCpfouCnpj())){
    	   list.add(new FieldMessage("cpfOuCnpj", "CPF Ínválido"));
       }
		
       if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.gedCod()) && !BR.IsValidCNPJ(objDto.getCpfouCnpj())){
    	   list.add(new FieldMessage("cpfOuCnpj", "CNPJ Ínválido"));
       }
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}