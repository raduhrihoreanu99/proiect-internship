/* tslint:disable */
export interface AppointmentDto {
  appointmentId?: number;
  appointmentStatus?: 'CANCELLED' | 'CREATED' | 'FINISHED';
  startDate?: string;
}
