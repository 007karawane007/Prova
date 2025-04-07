#include <stdio.h>
#include <stdlib.h>
#include <math.h>
//#include "lista_concatenata.h"

struct tree
{
    int key;
    struct tree *left;
    struct tree *right;
};

struct tree *create(int key);
struct tree *insert(struct tree *root, int key);
struct tree *find(struct tree *root, int key);
struct tree *rimuovi(struct tree *root, int key);
void destroy(struct tree *root);
//struct node *to_list(struct tree *root);

int main(){
    struct tree *r = create(1);
    printf("Chiave r=%d\n", r->key);
    r = insert(r, 2);
    r = insert(r, 3);
    printf("Chiave r=%d\n", r->key);
    printf("Chiave sx r=%d\n", r->left->key);
    printf("Chiave dx r=%d\n", r->right->key);
    struct tree *t = find(r, 1);
    printf("Chiave find t=%d\n", t->key);
    t = find(r, 2);
    printf("Chiave find t=%d\n", t->key);
    struct tree *s = rimuovi(r, 2);
    printf("Chiave r=%d\n", r->key);
    printf("Chiave r=%d\n", r->left->key);
    printf("Chiave r=%d\n", r->right->key);
    destroy(r);
    destroy(t);
    destroy(s);
}

struct tree *create(int key){
    struct tree *root = malloc(sizeof(struct tree));
    root->key = key;
    root->left = NULL;
    root->right = NULL;
    return root;
}

struct tree *insert(struct tree *root, int key){
    if(root->left == NULL){
        root->left = create(key);
    }else if(root->right == NULL){
        root->right = create(key);
    }else{
        int i = rand() % 2+1;
        if(i == 1){
            return insert(root->left, key);
        }else{
            return insert(root->right, key);
        }
    }
    return root;
}

struct tree *find(struct tree *root, int key){
    if(root == NULL){
        return NULL;
    }else if(root->key == key){
        return root;
    }else{
        return find(root->left, key);
        return find(root->right, key);
    }
}

struct tree *rimuovi(struct tree *root, int key){
    struct tree *k = find(root, key);
    free(k);
    return root;
}

void destroy(struct tree *root){
    if(root == NULL){
        return;
    }else{
        struct tree *sx = root->left;
        struct tree *dx = root->right;
        free(root);
        return destroy(sx);
        return destroy(dx);
    }
}

//struct node *to_list(struct tree *root){}