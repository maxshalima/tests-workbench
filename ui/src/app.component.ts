import {Component, OnInit} from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {HttpClient} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterModule, CommonModule, RouterOutlet],
    template: `<router-outlet></router-outlet>`
})
export class AppComponent implements OnInit{

    public isLoggedIn = false;
    public userProfile: KeycloakProfile | null = null;

    constructor(private keycloak: KeycloakService, private http: HttpClient) {}

    async ngOnInit() {
        this.isLoggedIn = await this.keycloak.isLoggedIn();
        if (this.isLoggedIn) {
            this.userProfile = await this.keycloak.loadUserProfile();
        }
    }

    login() {
        this.keycloak.login();
    }

    logout() {
        this.keycloak.logout();
    }



}
