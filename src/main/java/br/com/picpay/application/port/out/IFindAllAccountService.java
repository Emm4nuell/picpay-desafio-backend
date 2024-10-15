package br.com.picpay.application.port.out;

import br.com.picpay.application.domain.model.PageAccountDomain;

public interface IFindAllAccountService {
    PageAccountDomain execute(PageAccountDomain domain);
}
