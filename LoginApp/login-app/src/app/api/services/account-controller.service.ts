/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Account } from '../models/account';

/**
 * Account Controller
 */
@Injectable({
  providedIn: 'root',
})
class AccountControllerService extends __BaseService {
  static readonly registerAccountUsingPOSTPath = '/account/register';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * registerAccount
   * @param account account
   * @return OK
   */
  registerAccountUsingPOSTResponse(account: Account): __Observable<__StrictHttpResponse<Account>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = account;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/account/register`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Account>;
      })
    );
  }
  /**
   * registerAccount
   * @param account account
   * @return OK
   */
  registerAccountUsingPOST(account: Account): __Observable<Account> {
    return this.registerAccountUsingPOSTResponse(account).pipe(
      __map(_r => _r.body as Account)
    );
  }
}

module AccountControllerService {
}

export { AccountControllerService }
