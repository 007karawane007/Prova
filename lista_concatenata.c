#include <stdio.h>
#include <stdlib.h>

struct node{
	int data;
	struct node *next;
};

struct node *create(int data);
int length(struct node *head, int len);
struct node *find(struct node *head, int data);
struct node *last(struct node *head);
struct node *append(struct node *head1, struct node *head2);
void destroy(struct node *head);

int main(){
	struct node *c = create(3);
	struct node *f = create(5);
	struct node *e = append(c, f);
	printf("Primo dato append=%d\n", e->data);
	printf("Secondo dato append=%d\n", e->next->data);
	printf("Lunghezza e=%d\n", length(e, 0));
	struct node *s = find(e, 5);
	printf("Dato nel find=%d\n", s->data);
	struct node *t = last(e);
	printf("Dato nel last=%d\n", t->data);
	destroy(c);
	destroy(f);
}

struct node *create(int data){
	struct node *ptr = malloc(sizeof(struct node));
	ptr->data = data;
	ptr->next = NULL;
	return ptr;
}

int length(struct node *head, int len){//len = 0 prima chiamata
	if(head == NULL){
		return len;
	}else{
		return length(head->next, len+1);
	}
}

struct node *find(struct node *head, int data){
	if(head == NULL){
		return NULL;
	}else{
		if(head->data == data){
			return head;
		}else{
			return find(head->next, data);
		}
	}
}

struct node *last(struct node *head){
	if(head == NULL){
		return NULL;
	}
	if(head->next == NULL){
		return head;
	}else{
		return last(head->next);
	}
}

struct node *append(struct node *head1, struct node *head2){
	if(head1 == NULL){
		return head2;
	}else{
		struct node *last1 = last(head1);
		last1->next = head2;
		return head1;
	}
}

void destroy(struct node *head){
	if(head == NULL){
		return;
	}
	struct node *prossimo = head->next;
	free(head);
	return destroy(prossimo);
}