import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipeDetailsListComponent } from './components/recipe-details-list/recipe-details-list.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { AddRecipedetailsComponent } from './components/add-recipedetails/add-recipedetails.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationDetailsComponent } from './components/registration-details/registration-details.component';
import { ProfileUpdateComponent } from './components/profile-update/profile-update.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'foodrecipedetailsAdmin', component: RecipeDetailsListComponent },
    { path: 'recipe-details-admin/:id', component: RecipeDetailsComponent },
    { path: 'recipe-details-user/:id', component: RecipeDetailsComponent },
    { path: 'add', component: AddRecipedetailsComponent },
    { path: 'logout', component: LoginComponent },
    { path: 'signup', component: RegistrationDetailsComponent },
    { path: 'foodrecipedetailsUser', component: RecipeDetailsListComponent },
    { path: 'changepassword', component: ChangePasswordComponent },
    { path: 'profileupdate', component: ProfileUpdateComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
