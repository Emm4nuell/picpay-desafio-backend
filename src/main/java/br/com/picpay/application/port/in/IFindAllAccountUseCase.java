package br.com.picpay.application.port.in;

import br.com.picpay.application.domain.model.PageAccountDomain;

import java.util.List;

public interface IFindAllAccountUseCase {
    PageAccountDomain execute(PageAccountDomain domain);
}
