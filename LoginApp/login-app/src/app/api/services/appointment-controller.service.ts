/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { AppointmentDto } from '../models/appointment-dto';

/**
 * Appointment Controller
 */
@Injectable({
  providedIn: 'root',
})
class AppointmentControllerService extends __BaseService {
  static readonly createPatientUsingPOSTPath = '/appointment/create';
  static readonly getAppointmentsByPatientIdUsingGETPath = '/appointment/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * createPatient
   * @param params The `AppointmentControllerService.CreatePatientUsingPOSTParams` containing the following parameters:
   *
   * - `patientId`: patientId
   *
   * - `appointmentDto`: appointmentDto
   *
   * @return OK
   */
  createPatientUsingPOSTResponse(params: AppointmentControllerService.CreatePatientUsingPOSTParams): __Observable<__StrictHttpResponse<AppointmentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.patientId != null) __params = __params.set('patientId', params.patientId.toString());
    __body = params.appointmentDto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/appointment/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AppointmentDto>;
      })
    );
  }
  /**
   * createPatient
   * @param params The `AppointmentControllerService.CreatePatientUsingPOSTParams` containing the following parameters:
   *
   * - `patientId`: patientId
   *
   * - `appointmentDto`: appointmentDto
   *
   * @return OK
   */
  createPatientUsingPOST(params: AppointmentControllerService.CreatePatientUsingPOSTParams): __Observable<AppointmentDto> {
    return this.createPatientUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as AppointmentDto)
    );
  }

  /**
   * getAppointmentsByPatientId
   * @param id id
   * @return OK
   */
  getAppointmentsByPatientIdUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Array<AppointmentDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/appointment/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<AppointmentDto>>;
      })
    );
  }
  /**
   * getAppointmentsByPatientId
   * @param id id
   * @return OK
   */
  getAppointmentsByPatientIdUsingGET(id: number): __Observable<Array<AppointmentDto>> {
    return this.getAppointmentsByPatientIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as Array<AppointmentDto>)
    );
  }
}

module AppointmentControllerService {

  /**
   * Parameters for createPatientUsingPOST
   */
  export interface CreatePatientUsingPOSTParams {

    /**
     * patientId
     */
    patientId: number;

    /**
     * appointmentDto
     */
    appointmentDto: AppointmentDto;
  }
}

export { AppointmentControllerService }
