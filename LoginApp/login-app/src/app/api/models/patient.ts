/* tslint:disable */
import { Appointment } from './appointment';
export interface Patient {
  appointments?: Array<Appointment>;
  birthdate: string;
  cnp: string;
  firstName?: string;
  lastName?: string;
  patientId?: number;
}
