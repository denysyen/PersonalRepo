import { Inject, Injectable, InjectionToken } from "@angular/core";

const TOKEN = "token";
const USER = "user";
  
@Injectable({
    providedIn: 'root'
})

export class StorageService {
    constructor() {}
    
    static saveToken(token: string): void {
        window.localStorage.removeItem(TOKEN);
        window.localStorage.setItem(TOKEN, token);
    }
    static getUserId(): string {
       const user = this.getUser();
       if(user == null) {
         return '';
       }
       return user.id; 
    }
    static saveUser(user: any): void {
        window.localStorage.removeItem(USER);
        window.localStorage.setItem(USER, JSON.stringify(user));
    }
    static getToken() {
        var getTokenFromStorage;
        // need to double check window object because I am  
        // running an app in server side while window object
        // come from the browser side, need to avoid pre-render issues 
        if (typeof window !== 'undefined') {
            getTokenFromStorage = localStorage ? localStorage.getItem(TOKEN) : null;
        }
        return getTokenFromStorage || null;
    }
    static getUser() {
        return JSON.parse(localStorage.getItem(USER) || '{}');
    }

    static getUserRole(): string {
        const user = this.getUser(); 
        if(user == null) return "";
        return user.role;  
    }

    static isAdminLoggedIn(): boolean {
        if(this.getToken() == null) return false;
        const role: string = this.getUserRole();
        return role == "ADMIN";
    }

    static isCustomerLoggedIn(): boolean {
        if(this.getToken() == null) return false;
        const role: string = this.getUserRole();
        return role == "CUSTOMER";
    }

    static logout(): void {
        window.localStorage.removeItem(TOKEN);
        window.localStorage.removeItem(USER);
    }

}