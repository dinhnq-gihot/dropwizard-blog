# GIT FLOW ⚙

## Bắt đầu:

- Tạo branch `main` và push code bắt đầu

```bash
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:<username>/<repo-name>.git
git push -u origin main
```

- Tạo branch `develop` và push code bắt đầu

```bash
git checkout -b develop
git push
```

## Quá trình phát triển

### Người tạo issue:

- Tạo 1 issue mới với title: `<name> - <issue>` gán assignee và label
- Đợi issue đc giải quyết và pull vào `develop`
- Confirm merge

### Người giải quyết issue:

- chuyển về branch `develop`
- tạo branch mới với `git checkout -b feature/<issue>`
- code các tính năng mới...
- add code mới `git add .`
- commit code mới `git commit -m "#<number-issue> - <issue>"`
- push code mới `git push --set-upstream origin feature/<issue>`
- Sau khi issue đc giải quyết thì `merge` branch mới vào branch `develop` với content `<something>. Refer: #<number-issue>`

### Kết quả:

- Chuyển qua branch `develop` bằng `git checkout develop`
- Pull code mới về `git pull`

## Đưa ra Production

- Xóa feature
  - Ở local `git branch -d feature/<issue>`
  - Ở remote `git push origin -d feature/<issue>`
- Tạo release `git checkout -b release-v<version-number>`
- push code mới pull ở `develop` lên `git push --set-upstream origin release-v<version-number>`
- `merge` về `main`
- pull code product ở `main` về
- Xóa release:
  - Ở local `git branch -d release-v<version-number>`
  - Ở remote `git push origin -d release-v<version-number>`
- Tạo tag và push code vào tag

```bash
git tag "v<version-number>"
git push --tags
git push
```

- Chuyển về branch `develop` để phát triển tính năng tiếp theo

## Tham khảo

<https://www.youtube.com/watch?v=vQgcl8VouLU&t=1180s&ab_channel=TipsJavascript>
