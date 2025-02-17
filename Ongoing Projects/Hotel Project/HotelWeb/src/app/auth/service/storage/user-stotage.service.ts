import { Injectable, Inject, PLATFORM_ID } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class UserStotageService {

  static memoryStorage: { [key: string]: string } = {};
  constructor() { 
  }

  static saveToken(token: string): void {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem(TOKEN);
      localStorage.setItem(TOKEN, token);
    } else {
      this.memoryStorage['token'] = token;
    }

  }

  static saveUser(user: any): void{
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem(USER);
      localStorage.setItem(USER, JSON.stringify(user));
    } else {
      this.memoryStorage['user'] = user;
    }

  }

  static getToken(): string{
    return localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    if (typeof localStorage !== 'undefined') { // Check if localStorage is available
      const storedUser = localStorage.getItem('user');
      if (storedUser) {
        return JSON.parse(storedUser);
      }
    }
    return null; // Or return a default value, or throw an error if appropriate
  }


  static getUSerId(): string{
    const user = this.getUser();
    if(user ==null) {
      return '';
    }
    return user.id;
  }

  
  static getUserRole(): string{
    const user = this.getUser();
    if(user ==null) {
      return '';
    }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if(this.getToken === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }


  static isCustomerLoggedIn(): boolean {
    if(this.getToken === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'CUSTOMER';
  }

  static signOut(): void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
