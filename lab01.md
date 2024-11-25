## 1. `WITHDRAW_MONEY` Use Case Description: Main Flow

| Attribute Name | Type   | Description                  |
|----------------|--------|------------------------------|
| `name`         | String | Name of the project manager. |
| `id`           | Int    | Unique identifier.           |
| `email`        | String | Email address.               |


| Attribute Name   | Type         | Description                                 |
|------------------|--------------|---------------------------------------------|
| `name`           | String       | Name of the project.                       |
| `projectId`      | Int          | Unique identifier for the project.         |
| `description`    | String       | Detailed description of the project.       |
| `status`         | String       | Current status (e.g., In Progress).        |
| `featureSet`     | List[Feature]| List of features associated with the project.|
