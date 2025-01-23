# import random
#
# # Lista zawodów
# jobs = [
#     "Backend Developer", "Frontend Developer", "Full Stack Developer",
#     "Data Scientist", "Machine Learning Engineer", "DevOps Engineer",
#     "Database Administrator", "Cloud Engineer", "Cybersecurity Specialist",
#     "Mobile Developer", "Game Developer", "IT Project Manager",
#     "Software Tester (QA Engineer)", "UX/UI Designer", "IT Business Analyst",
#     "Blockchain Developer", "Network Engineer", "Artificial Intelligence Researcher",
#     "Site Reliability Engineer (SRE)", "Technical Support Specialist"
# ]
#
# # Lista kwalifikacji z maksymalnym poziomem zaawansowania
# qualifications = [
#     ("Java", 3),
#     ("Python", 3),
#     ("JavaScript", 3),
#     ("C#", 3),
#     ("C++", 3),
#     ("Ruby", 2),
#     ("PHP", 2),
#     ("Swift", 3),
#     ("Kotlin", 2),
#     ("Go", 2),
#     ("Rust", 2),
#     ("TypeScript", 3),
#     ("SQL", 2),
#     ("HTML", 1),
#     ("CSS", 1),
#     ("Bootstrap", 1),
#     ("Spring Boot", 2),
#     ("Django", 2),
#     ("Flask", 2),
#     ("React", 2),
#     ("Angular", 2),
#     ("Vue.js", 2),
#     ("Node.js", 2),
#     ("Express.js", 2),
#     ("ASP.NET", 2),
#     ("Laravel", 2),
#     ("Symfony", 2),
#     ("Ruby on Rails", 2),
#     ("Docker", 2),
#     ("Kubernetes", 2),
#     ("AWS", 3),
#     ("Azure", 3),
#     ("GCP", 3),
#     ("Terraform", 2),
#     ("Ansible", 2),
#     ("Puppet", 2),
#     ("Chef", 2),
#     ("Jenkins", 2),
#     ("Git", 1),
#     ("GitHub", 1),
#     ("GitLab", 1),
#     ("Bitbucket", 1),
#     ("JIRA", 2),
#     ("Confluence", 1),
#     ("Slack", 1),
#     ("Trello", 1),
#     ("Asana", 1)
# ]
#
# # Generowanie danych
# data = []
#
# for job in jobs:
#     # Losowo wybierz kilka kwalifikacji dla danego zawodu
#     num_qualifications = random.randint(1, len(qualifications))
#     selected_qualifications = random.sample(qualifications, num_qualifications)
#
#     for qualification, max_level in selected_qualifications:
#         level = random.randint(1, max_level)
#         data.append((job, qualification, level))
#
# # Wyświetlanie danych w konsoli
# print("Zawód,Kwalifikacje,Poziom zaawansowania")
# for row in data:
#     print(f"{row[0]},{row[1]},{row[2]}")
