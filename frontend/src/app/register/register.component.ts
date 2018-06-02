import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import * as _ from 'lodash';
import * as moment from 'moment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  genderOptions: { value: string; text: string }[] = [
    {value: "FEMALE", text: "kobieta"},
    {value: "MALE", text: "mężczyzna"}
  ];

  birthyearOptions: number[] = _.range(moment().year() - 18, 1950);

  birthmonthOptions: { value: string; text: string }[] = [
    {value: "1", text: "styczeń"},
    {value: "2", text: "luty"},
    {value: "3", text: "marzec"},
    {value: "4", text: "kwiecień"},
    {value: "5", text: "maj"},
    {value: "6", text: "czerwiec"},
    {value: "7", text: "lipiec"},
    {value: "8", text: "sierpień"},
    {value: "9", text: "wrzesień"},
    {value: "10", text: "październik"},
    {value: "11", text: "listopad"},
    {value: "12", text: "grudzień"}
  ];

  registerForm: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'username': new FormControl("", [
        Validators.required,
        Validators.pattern(/^[A-Za-zĘÓĄŚŁŻŹĆŃęóąśłżźćń]{2,16}$/)
      ]),
      'gender': new FormControl("", [
        Validators.required,
      ]),
      'birthyear': new FormControl("", [
        Validators.required,
      ]),
      'birthmonth': new FormControl("", [
        Validators.required,
      ]),
      'emailGroup': new FormGroup({
        'email': new FormControl("", [
          Validators.required,
          Validators.email
        ]),
        'emailRepeated': new FormControl("", [
          Validators.required
        ])
      }, RegisterComponent.emailConfirmValidator),
      'passwordGroup': new FormGroup({
        'password': new FormControl("", [
          Validators.required,
          Validators.compose([
            Validators.minLength(8),
            Validators.pattern(/[A-ZĘÓĄŚŁŻŹĆŃ]+/),
            Validators.pattern(/[0-9]+/)
          ])
        ]),
        'passwordRepeated': new FormControl("", [
          Validators.required
        ])
      }, RegisterComponent.passwordConfirmValidator),
      'acceptRegulations': new FormControl("", [
        Validators.requiredTrue
      ]),
      'claimPersonalData': new FormControl("", [
        Validators.requiredTrue
      ])
    });
  }

  static emailConfirmValidator(emailGroup: AbstractControl): { [s: string]: boolean } {
    let email = emailGroup.get('email');
    let emailRepeated = emailGroup.get('emailRepeated');
    if (email.touched && email.value !== emailRepeated.value) {
      emailRepeated.setErrors({'emailConfirm': true});
    }
    return null;
  }

  static passwordConfirmValidator(passwordGroup: AbstractControl): { [s: string]: boolean } {
    let password = passwordGroup.get('password');
    let passwordRepeated = passwordGroup.get('passwordRepeated');
    if (password.touched && password.value !== passwordRepeated.value) {
      passwordRepeated.setErrors({'passwordConfirm': true});
    }
    return null;
  }

  onSubmit() {
    console.log(this.registerForm);
  }

  get username(): AbstractControl {
    return this.registerForm.get('username');
  }

  get emailGroup(): AbstractControl {
    return this.registerForm.get('emailGroup');
  }

  get email(): AbstractControl {
    return this.emailGroup.get('email');
  }

  get emailRepeated(): AbstractControl {
    return this.emailGroup.get('emailRepeated');
  }

  get passwordGroup(): AbstractControl {
    return this.registerForm.get('passwordGroup');
  }

  get password(): AbstractControl {
    return this.passwordGroup.get('password');
  }

  get passwordRepeated(): AbstractControl {
    return this.passwordGroup.get('passwordRepeated');
  }

  get acceptRegulations(): AbstractControl {
    return this.registerForm.get('acceptRegulations');
  }

  get claimPersonalData(): AbstractControl {
    return this.registerForm.get('claimPersonalData');
  }

}
