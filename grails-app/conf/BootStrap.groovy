class BootStrap {

    def crmCoreService
    def crmAccountService
    def crmSecurityService
    def crmContactService
    def crmContentService
    def crmPluginService
    def grailsNavigation
    def crmTaskService
    def gr8ConfService

    def init = { servletContext ->

        // Add some items to the main horizontal menu
        grailsNavigation.registerNavigation({
            main {
                crmCalendar controller: 'crmCalendar', action: 'index', title: 'crmCalendar.index.label', order: 81
            }
        });

        // crmContact:show << documents
        crmPluginService.registerView('crmContact', 'show', 'tabs',
                [id: "documents",
                        index: 500,
                        permission: "crmContact:show",
                        label: "crmContact.tab.documents.label",
                        template: '/crmContent/embedded',
                        plugin: "crm-content-ui",
                        model: {
                            def result = crmContentService.findResourcesByReference(crmContact)
                            return [bean: crmContact, list: result, totalCount: result.size(),
                                    reference: crmCoreService.getReferenceIdentifier(crmContact),
                                    openAction: 'show', multiple: true]
                        }]
        )

        crmPluginService.registerView('crmContact', 'show', 'tabs',
                [id: "tasks", index: 300, permission: "crmTask:show", label: "crmTask.index.label", template: '/crmTask/list', plugin: "crm-task-ui", model: {
                    def result = crmTaskService.list([reference: crmContact], [sort: 'startTime', order: 'asc'])
                    def rid = crmCoreService.getReferenceIdentifier(crmContact)
                    return [bean: crmContact, reference: rid, result: result, totalCount: result.totalCount]
                }]
        )

        def admin = crmSecurityService.createUser([username: "admin", password: "admin",
                email: "firstname.lastname@email.com", name: "Admin", enabled: true])

        crmSecurityService.addPermissionAlias("permission.all", ["*:*"])

        crmSecurityService.runAs(admin.username) {
            def account = crmAccountService.createAccount([status: "active"])
            def tenant = crmSecurityService.createTenant(account, "GR8 Contacts", [locale: Locale.ENGLISH])
            crmSecurityService.runAs(admin.username, tenant.id) {
                crmSecurityService.addPermissionToUser("permission.all")

                // Create some demo data.
                def type1 = crmContactService.createRelationType(name: "Organizer", param: "organizer", true)
                def type2 = crmContactService.createRelationType(name: "Speaker", param: "speaker", true)
                def type3 = crmContactService.createRelationType(name: "Owner", param: "owner", true)

                def gr8conf = crmContactService.createCompany(name: "GR8Conf", email: "info@gr8conf.org",
                        address: [address1: "IT-University", city: 'Copenhagen', country: "DK"], true)
                def soren = crmContactService.createPerson(firstName: "Søren", lastName: "Berg Glasius",
                        email: "sbglasius@gr8conf.org", title: "Awesome Organizer", true)
                crmContactService.addRelation(soren, gr8conf, type1, true, 'Awesome organizer of GR8Conf Europe')

                def greachconf = crmContactService.createCompany(name: "Greach", email: "crew@greachconf.com",
                        address: [address1: "ESCUELA UNIVERSITARIA DE INFORMÁTICA", address2: 'C/ Arboleda', postalCode: 'S/N 28031', city: 'Madrid', country: "ES"],
                        description: 'GREACH IS A 100% ENGLISH CONFERENCE IN A 100% SPANISH CITY\nGroovy, Grails, Griffon, Gpars, Gradle, Spock, Vert.x, Gaelyk and many more.', true)
                def ivan = crmContactService.createPerson(firstName: "Iván", lastName: "López",
                        email: "lopez.ivan@gmail.com", title: "Awesome Organizer", true)
                crmContactService.addRelation(ivan, greachconf, type1, true, 'Awesome organizer of Greach')

                def technipelago = crmContactService.createCompany(name: "Technipelago AB", email: "info@technipelago.se",
                        url: 'www.technipelago.se', address: [city: 'Djurhamn', country: "SE"], true)
                def goran = crmContactService.createPerson(firstName: "Göran", lastName: "Ehrsson",
                        email: "goran@technipelago.se", title: "Developer", true)
                crmContactService.addRelation(goran, technipelago, type3, true)
                crmContactService.addRelation(goran, greachconf, type2, false, "Cut your Grails application to pieces")
            }
        }
    }

    def destroy = {
    }
}
