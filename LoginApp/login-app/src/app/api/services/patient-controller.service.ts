/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { PatientDto } from '../models/patient-dto';
import { Patient } from '../models/patient';

/**
 * Patient Controller
 */
@Injectable({
  providedIn: 'root',
})
class PatientControllerService extends __BaseService {
  static readonly getAllPatientsUsingGETPath = '/patients';
  static readonly createPatientUsingPOST1Path = '/patients/create';
  static readonly deletePatientUsingDELETEPath = '/patients/delete/{patient_id}';
  static readonly getPatientsByFirstNameUsingGETPath = '/patients/name';
  static readonly updatePatientUsingPUTPath = '/patients/update';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllPatients
   * @return OK
   */
  getAllPatientsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<PatientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/patients`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<PatientDto>>;
      })
    );
  }
  /**
   * getAllPatients
   * @return OK
   */
  getAllPatientsUsingGET(): __Observable<Array<PatientDto>> {
    return this.getAllPatientsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<PatientDto>)
    );
  }

  /**
   * createPatient
   * @param patient patient
   * @return OK
   */
  createPatientUsingPOST1Response(patient: Patient): __Observable<__StrictHttpResponse<Patient>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = patient;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/patients/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Patient>;
      })
    );
  }
  /**
   * createPatient
   * @param patient patient
   * @return OK
   */
  createPatientUsingPOST1(patient: Patient): __Observable<Patient> {
    return this.createPatientUsingPOST1Response(patient).pipe(
      __map(_r => _r.body as Patient)
    );
  }

  /**
   * deletePatient
   * @param patient_id patient_id
   */
  deletePatientUsingDELETEResponse(patientId: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/patients/delete/${encodeURIComponent(String(patientId))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * deletePatient
   * @param patient_id patient_id
   */
  deletePatientUsingDELETE(patientId: number): __Observable<null> {
    return this.deletePatientUsingDELETEResponse(patientId).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * getPatientsByFirstName
   * @param params The `PatientControllerService.GetPatientsByFirstNameUsingGETParams` containing the following parameters:
   *
   * - `lastName`: lastName
   *
   * - `firstName`: firstName
   *
   * @return OK
   */
  getPatientsByFirstNameUsingGETResponse(params: PatientControllerService.GetPatientsByFirstNameUsingGETParams): __Observable<__StrictHttpResponse<Array<PatientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.lastName != null) __params = __params.set('lastName', params.lastName.toString());
    if (params.firstName != null) __params = __params.set('firstName', params.firstName.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/patients/name`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<PatientDto>>;
      })
    );
  }
  /**
   * getPatientsByFirstName
   * @param params The `PatientControllerService.GetPatientsByFirstNameUsingGETParams` containing the following parameters:
   *
   * - `lastName`: lastName
   *
   * - `firstName`: firstName
   *
   * @return OK
   */
  getPatientsByFirstNameUsingGET(params: PatientControllerService.GetPatientsByFirstNameUsingGETParams): __Observable<Array<PatientDto>> {
    return this.getPatientsByFirstNameUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<PatientDto>)
    );
  }

  /**
   * updatePatient
   * @param patient patient
   * @return OK
   */
  updatePatientUsingPUTResponse(patient: Patient): __Observable<__StrictHttpResponse<Patient>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = patient;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/patients/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Patient>;
      })
    );
  }
  /**
   * updatePatient
   * @param patient patient
   * @return OK
   */
  updatePatientUsingPUT(patient: Patient): __Observable<Patient> {
    return this.updatePatientUsingPUTResponse(patient).pipe(
      __map(_r => _r.body as Patient)
    );
  }
}

module PatientControllerService {

  /**
   * Parameters for getPatientsByFirstNameUsingGET
   */
  export interface GetPatientsByFirstNameUsingGETParams {

    /**
     * lastName
     */
    lastName?: string;

    /**
     * firstName
     */
    firstName?: string;
  }
}

export { PatientControllerService }
