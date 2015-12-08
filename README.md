# Grails-CRM QuickStart

This is a fork of a demo application based on the Grails Web Application Framework and the [GR8 CRM plugin suite](http://gr8crm.github.io/).
It was first presented at GR8Conf Europe 2014 by Göran Ehrsson @goeh.

This implementation will be for a small community collaborative to help small business start using CRM and running their nascent organizations. 

The objective of this endeavor is to document how-to use the Grails-CRM as implemented code-base in a 3rd party non-orginal developer. GR8 CRM is a collection of Grails plugins and there is currently no ready packaged CRM ”product”. The only reference implementations that exists are the different demo applications found on gr8crm.github.io.  This is an attempt to move a working version outside the realm of the orginal team. 
 
It would be great to have OOTB application for others to use. Today GR8 CRM cannot compete with vTiger or Sugar CRM because the lack of resources, both developer resources and project lead. If we can change that and make something great about it, would people very happy.
The goal is to get a working version for our clients using Grails-CRM and document the process so as to help a community of others benefit from our lessons learned. 

## How to 
This repo is already a Fork of the original demo code. 

* Java JDK 1.7x
* Grails 2.4.5
* Tomcat 8. (28 and 30)
* git clone -b m3-greach https://github.com/kmacpher67/gr8contact.git m3-greach

## History
The application is a simple Contact Management application where you can manage your companies and contacts.

The repository has five branches: master and m1-m4 that represents snapshots in time when developing the application.

**master**: A plain Grails application, created with *grails create-app*

**m1**: The plugin *crm-contact-ui* was installed and contact (company/person) CRUD features are available.

**m2**: The plugin *crm-content-ui* was installed and documents/files can now be attached to contacts.

**m3**: The plugin *crm-task-ui* was installed and tasks/appointments with contacts can now be scheduled.

**m4**: The plugin *cookie-layout* was installed and two *themes* were created to show that UI can change depending on sub-domain.

![GR8 Contact Manager](src/docs/images/crm-contact-show.png)

## Greach 2015 - The Groovy spanish conf

Four new branches were created for the speak
[Cut your grails application to pieces - build feature plugins](http://greachconf.com/speakers/goran-ehrsson-cut-your-grails-application-to-pieces-build-feature-plugins/)
at [Greach 2015](http://greachconf.com).

**m1-greach**: Same as **m1** but with Greach theme applied.

**m2-greach**: Same as **m2** but with Greach theme applied.

**m3-greach**: Same as **m3** but with Greach theme applied and option to import Greach 2015 speakers as contacts.

**m4-greach**: Same as **m4** but with Greach theme available. Three themes (Greach, Gr8ConfEU and Gr8ConfUS) available at run-time.
