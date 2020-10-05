import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }

  registrationForm: FormGroup;

  ngOnInit(): void {

    this.registrationForm = this.formBuilder.group({
      name: ['', Validators.required],
      amount: ['', Validators.required],
      description: ['', Validators.required],
      date: ['', Validators.required],
    });

  }

  onSubmit(){

  }

}
