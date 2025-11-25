import { ValidatorFn, AbstractControl } from '@angular/forms';

export const nonEmptyTrim: ValidatorFn = (c: AbstractControl) => {
  const v = c.value;
  return v && v.toString().trim().length > 0 ? null : { required: true };
};
