db.role.insertMany([
  {
    _id: "admin",
    name: "管理员"
  },
  {
    _id: "VE",
    name: "Viewer"
  },
  {
    _id: "RE",
    name: "RE工程师"
  },
  {
    _id: "FE",
    name: "FE工程师"
  }
]
);

db.group.insertOne(
  {
    _id: "1",
    name: "管理员"
  }
);

db.user.insertOne({
  name: "admin",
  password: "1f22f6ce6e58a7326c5b5dd197973105",
  realName: "管理员",
  phone: "18654551561",
  role: "admin",
  group: "1",
  alive: true,
  _class: "com.synebula.zeus.domain.model.rbac.User"
});

db.system.insertOne({
  _id: "1",
  name: "web",
  uri: "",
  order: 1,
})

db.page.insertMany([
  { "_id": "1", "name": "groups", "uri": "/groups", "parent": "0", "system": "1", "order": 5, "icon": "group" },
  { "_id": "2", "name": "roles", "uri": "/roles", "parent": "0", "system": "1", "order": 6, "icon": "manual" },
  { "_id": "3", "name": "users", "uri": "/users", "parent": "0", "system": "1", "order": 7, "icon": "user" },
  { "_id": "4", "name": "sheet", "uri": "/repair/sheets/all", "parent": "0", "system": "1", "order": 1, "icon": "sheet" },
  { "_id": "5", "name": "sheet", "uri": "/repair/sheets/permission", "parent": "1", "system": "1", "order": 2, "icon": "sheet" },
  { "_id": "6", "name": "spare", "uri": "/repair/spares/all", "parent": "1", "system": "1", "order": 3, "icon": "setting" },
  { "_id": "7", "name": "spare", "uri": "/repair/spares/permission", "parent": "1", "system": "1", "order": 4, "icon": "setting" },
  { "_id": "8", "name": "manual", "uri": "/manual", "parent": "0", "system": "1", "order": 8, "icon": "manual" }
])

db.authority.insertMany([
  { "role": "admin", "resource": "1", "type": "System", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "1", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "2", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "3", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "4", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "5", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "6", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "7", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
  { "role": "admin", "resource": "8", "type": "Page", "authority": "Allow", "alive": true, "_class": "com.synebula.zeus.domain.model.rbac.Authority" },
])

